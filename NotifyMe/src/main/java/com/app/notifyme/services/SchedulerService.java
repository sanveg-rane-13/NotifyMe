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

@Component
public class SchedulerService {

	private List<Product> prodList = Collections.synchronizedList(new ArrayList<>());

	@Autowired
	private ProductRepository productRepository;

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

			PriceExtractor pExtract = new PriceExtractor(product,productRepository);
			executorService.execute(pExtract);
		}
	}
}
