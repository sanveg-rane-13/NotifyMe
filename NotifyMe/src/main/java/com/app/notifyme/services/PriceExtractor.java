package com.app.notifyme.services;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.app.notifyme.models.Product;
import com.app.notifyme.repositories.ProductRepository;

public class PriceExtractor implements Runnable {

	private ProductRepository productRepository;

	private final String url;
	private final String xPath;
	private Product product;

	public PriceExtractor(Product product, ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
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
			System.out.println("Could not find price");
		}

		if (doc == null) {
			// System.out.println(this.url);
			System.out.println("document null");
		} else if (elementPrice == null) {
			System.out.println("element null");
		}

		if (elementPrice != null) {
			String text = elementPrice.text().trim();

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

			this.product.setCurrentPrice(price);
			System.out.println(price);
			this.productRepository.save(product);
		}
	}

}
