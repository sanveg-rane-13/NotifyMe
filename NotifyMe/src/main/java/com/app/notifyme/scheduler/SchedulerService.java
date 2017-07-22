package com.app.notifyme.scheduler;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.app.notifyme.models.FaultStatus;
import com.app.notifyme.models.Previousstat;
import com.app.notifyme.models.Product;
import com.app.notifyme.models.Productstat;
import com.app.notifyme.repositories.PreviousStatRepository;
import com.app.notifyme.repositories.ProductRepository;
import com.app.notifyme.repositories.ProductStatRepository;
import com.app.notifyme.services.ErrorNotificationService;
import com.app.notifyme.services.NotifyUserService;

/*
 * Component to schedule check on product prices from various websites
 * Product table contains product url and xpath to the price
 * Saving the product list locally
 * The product list will be updated only on changes to the products table to reduce access on the database
 * The scheduler will check price for products on set time by starting threads for different product elements in the arraylist.
 */

@Component
public class SchedulerService {

	final static Logger logger = Logger.getLogger(SchedulerService.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductStatRepository productStatRepository;

	@Autowired
	private PreviousStatRepository previousStatRepository;

	@Autowired
	private NotifyUserService notifyUserService;

	@Autowired
	ErrorNotificationService errorNotificationService;

	private List<Product> productsList = new CopyOnWriteArrayList<>();
	private ExecutorService executorService = Executors.newFixedThreadPool(10);

	// @Scheduled(fixedRate = 10000)
	public void updateProductList() {
		logger.info("Executing: updateProductList() ---------------------------------");
		this.productsList = productRepository.findAll();
	}

	// Schedule the method for each minute
	// @Scheduled(cron = "0 0/1 * * * ? ")
	@Scheduled(fixedRate = 10000)
	private void checkProductsPrice() {
		logger.info("Executing: checkProductsPrice() ---------------------------------");
		if (productsList.size() == 0) {
			updateProductList();
		}

		Iterator<Product> iterator = this.productsList.iterator();

		while (iterator.hasNext()) {
			Product product = iterator.next();
			if (product.getFaultStatus() == FaultStatus.NORMAL.getFaultStatus() && product.getTrackCount() > 0) {
				PriceExtractor pExtract = new PriceExtractor(product, productRepository, productStatRepository, errorNotificationService,
						notifyUserService);
				executorService.execute(pExtract);
			}
		}
	}

	// Schedule the process every hour
	@Scheduled(cron = "0 0 0/1 1/1 * ?")
	// @Scheduled(fixedRate = 20000, initialDelay = 5000)
	public void updateProductStatsTable() {
		logger.info("Executing: updateProductStatsTable() ---------------------------------");
		Iterator<Product> iterator = this.productsList.iterator();

		while (iterator.hasNext()) {
			Product product = iterator.next();

			Productstat productStat = new Productstat();
			productStat.setProduct(product);
			productStat.setPrice(product.getCurrentPrice());

			java.util.Date today = new java.util.Date();
			java.sql.Time time = new java.sql.Time(today.getTime());
			logger.info("updated stats table: " + product.getProductId() + " at " + time);
			productStat.setTime(time);

			this.productStatRepository.save(productStat);
		}
	}

	// Schedule the process at particular time
	@Scheduled(cron = "0 00 18 * * ?", zone = "Asia/Kolkata")
	// @Scheduled(fixedRate = 45000, initialDelay = 5000)
	public void updateProductStatsFile() {
		logger.info("Executing: updateProductStatsFile() ---------------------------------");
		Iterator<Product> i = this.productsList.iterator();

		while (i.hasNext()) {
			int prodId = i.next().getProductId();
			double prodMinVal = this.productStatRepository.findProductMinValue(prodId);
			Productstat prodStat = this.productStatRepository.findMinValueStat(prodMinVal);
			Previousstat prevStat = new Previousstat();

			prevStat.setDate(Date.valueOf(LocalDate.now()));
			prevStat.setMinimumPrice(prodMinVal);
			prevStat.setProduct(prodStat.getProduct());

			this.previousStatRepository.save(prevStat);
		}
		productStatRepository.deleteAll();
	}
}
