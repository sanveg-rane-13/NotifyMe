package com.app.notifyme.scheduler;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.app.notifyme.models.FaultStatus;
import com.app.notifyme.models.Product;
import com.app.notifyme.models.Productstat;
import com.app.notifyme.repositories.ProductRepository;
import com.app.notifyme.repositories.ProductStatRepository;
import com.app.notifyme.services.ErrorNotificationService;
import com.app.notifyme.services.NotifyUserService;

/*
 * Thread that gets product price from the url set in the product table.
 * Gets the page using JSOUP library and stores in a document object.
 * Searches for the xpath using getElement by id method.
 * Converts the obtained string into double price value.
 * Updates the product price injected from the scheduler service.
 * 
 * Takes current price and date
 * Then add statistics about the product into the Productstats table
 */

public class PriceExtractor implements Runnable {

	final static Logger logger = Logger.getLogger(PriceExtractor.class);

	private NotifyUserService notifyUserService;

	private ProductStatRepository productStatRepository;
	private ProductRepository productRepository;
	private ErrorNotificationService errorNotificationService;

	private final String url;
	private final String xPath;
	private Product product;

	public PriceExtractor(Product product, ProductRepository productRepository,
			ProductStatRepository productStatRepository, ErrorNotificationService errorNotificationService,
			NotifyUserService notifyUserService) {
		super();
		this.productRepository = productRepository;
		this.productStatRepository = productStatRepository;
		this.notifyUserService = notifyUserService;
		this.errorNotificationService = errorNotificationService;
		this.product = product;
		this.url = product.getUrl();
		this.xPath = product.getXPath();
	}

	@Override
	public void run() {

		Connection connection = null;
		Document doc = null;
		Element elementPrice = null;

		try {
			connection = Jsoup.connect(this.url);
		} catch (Exception ex) {
			System.out.println("notify error in url " + this.url);
			errorNotificationService.notifyError(this.product, FaultStatus.URL_BROKEN);
			return;
		}

		try {
			doc = connection.userAgent("Mozilla").timeout(20000).get();
		} catch (IOException ex) {
			System.out.println("Cannot read document for url: " + this.url);
			return;
		}
		if (doc != null) {
			elementPrice = doc.getElementById(this.xPath);
		}

		if (elementPrice == null) {
			System.out.println("notify error in xpath " + this.xPath);
			errorNotificationService.notifyError(product, FaultStatus.XPATH_BROKEN);
			return;
		}

		// try {
		// doc = Jsoup.connect(this.url).userAgent("Mozilla").timeout(20000).get();
		// if (doc != null) {
		// elementPrice = doc.getElementById(this.xPath);
		// }
		//
		// } catch (IOException e) {
		// logger.warn(this.product.getProductId() + " -> " + "Could not find price");
		// logger.warn(e);
		// }
		//
		// // If document not available.
		// if (doc == null) {
		// System.out.println("document null");
		// } else if (elementPrice == null) {
		// System.out.println("element null");
		// }

		// If element parsed correctly from the page
		if (elementPrice != null) {
			String text = elementPrice.text().trim();

			// Convert String to double price value
			String beg = text.substring(0, text.lastIndexOf('.'));
			String end = text.substring(text.lastIndexOf('.'));

			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < beg.length(); i++) {
				char c = beg.charAt(i);
				if (Character.isDigit(c)) {
					builder.append(c);
				}
			}
			double price = Double.parseDouble(builder.toString() + end);

			logger.info(this.product.getProductId() + " --> " + price);
			double savedPrice = this.product.getCurrentPrice();

			if (savedPrice != price) {
				this.product.setCurrentPrice(price);
				int productId = this.product.getProductId();
				// this.notifyUserService.checkCriteriaMatch(productId, price);

				this.productRepository.save(product);
			}

			if (savedPrice > price) {

				// Adding details to the productStats table
				Productstat productStat = new Productstat();
				productStat.setProduct(this.product);
				productStat.setPrice(price);

				java.util.Date today = new java.util.Date();
				java.sql.Time time = new java.sql.Time(today.getTime());
				logger.info("updated stats table: " + product.getProductId() + " at " + time);
				productStat.setTime(time);

				this.productStatRepository.save(productStat);
			}
		}
	}

}
