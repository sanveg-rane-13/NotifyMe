package com.app.notifyme.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.app.notifyme.models.Product;
import com.app.notifyme.repositories.ProductRepository;
import com.app.notifyme.repositories.ProductStatRepository;


/*
 * Component to schedule check on product prices from various websites
 * Product table contains product url and xpath to the price
 * Saving the product list locally
 * The product list will be updated only on changes to the products table to reduce access on the database
 * The scheduler will check price for products on set time by starting threads for different product elements in the arraylist.
 */

@Component
public class SchedulerService {

	private List<Product> prodList = Collections.synchronizedList(new ArrayList<>());

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductStatRepository  productStatRepository;

	private ExecutorService executorService = Executors.newFixedThreadPool(10);

	@Scheduled(fixedRate = 25000)
	public void updateProductList() {
		System.out.println("Product array updated");
		this.prodList = productRepository.findAll();
	}

	@Scheduled(fixedRate = 10000, initialDelay = 100)
	public void checkProductsPrice() {

		System.out.println("Printing list: ");

		for (Product product : this.prodList) {

			PriceExtractor pExtract = new PriceExtractor(product,productRepository, productStatRepository);
			executorService.execute(pExtract);
		}
	}
}
