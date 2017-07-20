package com.app.notifyme.services;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.app.notifyme.models.Product;
import com.app.notifyme.models.Productstat;
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

	// Injecting dependencies
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductStatRepository productStatRepository;

	private List<Product> productsList = new CopyOnWriteArrayList<>();
	private ExecutorService executorService = Executors.newFixedThreadPool(10);

	// @Scheduled(fixedRate = 10000)
	public void updateProductList() {
		System.out.println("Updating products arraylist");
		this.productsList = productRepository.findAll();
	}

	// Schedule the method for each minute
	// @Scheduled(cron = "0 0/1 * * * ? ")
	@Scheduled(fixedRate = 10000)
	private void checkProductsPrice() {

		if (productsList.size() == 0) {
			updateProductList();
		}

		System.out.println("Printing product prices: ");

		Iterator<Product> iterator = this.productsList.iterator();

		while (iterator.hasNext()) {
			Product product = iterator.next();
			PriceExtractor pExtract = new PriceExtractor(product, productRepository, productStatRepository);
			executorService.execute(pExtract);
		}
	}

	// Schedule the process every hour
	// @Scheduled(cron = "0 0/1 * 1/1 * ?")
	@Scheduled(fixedRate = 20000, initialDelay = 5000)
	public void updateProductStatsTable() {
		System.out.println("Updating statistics");
		Iterator<Product> iterator = this.productsList.iterator();

		while (iterator.hasNext()) {
			Product product = iterator.next();

			Productstat productStat = new Productstat();
			productStat.setProduct(product);
			productStat.setPrice(product.getCurrentPrice());

			java.util.Date today = new java.util.Date();
			java.sql.Time time = new java.sql.Time(today.getTime());
			System.out.println("updated stats table: " + product.getProductId() + " at " + time);
			productStat.setTime(time);

			this.productStatRepository.save(productStat);
		}
		updateProductStatsFile();
	}

	// Schedule the process at particular time
	// @Scheduled(cron = "0 04 18 * * ?", zone = "Asia/Kolkata")
	public void updateProductStatsFile() {
		Iterator<Product> i = this.productsList.iterator();

		while (i.hasNext()) {
			int prodId = i.next().getProductId();
			System.out.print("updated stats file: " + prodId + " minPrice: ");
			double prodMinVal = this.productStatRepository.findProductMinValue(prodId);
			System.out.println(prodMinVal);
			Productstat prodStat = this.productStatRepository.findMinValueStat(prodMinVal);
			System.out.println(prodStat);
		}
	}
}
