package com.app.notifyme.services;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.app.notifyme.models.Product;
import com.app.notifyme.models.Productstat;
import com.app.notifyme.repositories.ProductRepository;
import com.app.notifyme.repositories.ProductStatRepository;

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

	private ProductStatRepository productStatRepository;
	private ProductRepository productRepository;

	private final String url;
	private final String xPath;
	private Product product;

	public PriceExtractor(Product product, ProductRepository productRepository,
			ProductStatRepository productStatRepository) {
		super();
		this.productRepository = productRepository;
		this.productStatRepository = productStatRepository;
		this.product = product;
		this.url = product.getUrl();
		this.xPath = product.getXPath();
	}

	@Override
	public void run() {
		Document doc = null;
		Element elementPrice = null;

		try {
			doc = Jsoup.connect(this.url).get();
			if (doc != null) {
				elementPrice = doc.getElementById(this.xPath);
			}

		} catch (IOException e) {
			System.out.println(this.product.getProductId() + " -> " + "Could not find price");
		}

		// If document not available.
		if (doc == null) {
			// System.out.println(this.url);
			System.out.println("document null");
		} else if (elementPrice == null) {
			System.out.println("element null");
		}

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

			System.out.println(this.product.getProductId() + " -> " + price);
			double savedPrice = this.product.getCurrentPrice();

			if (savedPrice != price) {
				this.product.setCurrentPrice(price);
				this.productRepository.save(product);
			}

			if (savedPrice > price) {
				// Adding details to the productStats table
				Productstat productStat = new Productstat();
				productStat.setProduct(this.product);
				productStat.setPrice(price);

				java.util.Date today = new java.util.Date();
				java.sql.Time time = new java.sql.Time(today.getTime());
				System.out.println("updated stats table: " + product.getProductId() + " at " + time);
				productStat.setTime(time);

				this.productStatRepository.save(productStat);
			}
		}
	}

}
