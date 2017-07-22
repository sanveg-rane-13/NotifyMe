package com.app.notifyme.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.notifyme.models.Product;
import com.app.notifyme.repositories.ProductRepository;
import com.app.notifyme.scheduler.SchedulerService;
import com.app.notifyme.services.MostSearchedProductService;
import com.app.notifyme.services.RenderPageService;

/*
 * Service to add a new product in the products table.
 * The service checks whether the product with same exists in the table.
 * If no product exists then new product is added to the table in order to track price.
 * If product exists then the track count is updated.
 * Handles all the product related services.
 */

@RestController
public class ProductController {

	final static Logger logger = Logger.getLogger(ProductController.class);
	
	private final int initialTrackCount = 1;
	private final int initiaFaultStatus = 0;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SchedulerService schedulerService;

	@Autowired
	RenderPageService renderPageService;

	@Autowired
	MostSearchedProductService mostSearchedProductService;

	public int addProduct(Product product) {
		String url = product.getUrl();
		int id;

		// Check if product with same url exists
		Product existingProd = productRepository.findProduct(url);

		if (existingProd != null) {
			int count = existingProd.getTrackCount();
			existingProd.setTrackCount(count + 1);
			productRepository.save(existingProd);
			logger.info("updated product count: " + existingProd.getProductName());
			id = existingProd.getProductId();
		} else {
			product.setTrackCount(initialTrackCount);
			product.setFaultStatus(initiaFaultStatus);
			productRepository.save(product);
			logger.info("added product" + product.getProductName());
			id = product.getProductId();
		}
		schedulerService.updateProductList();
		return id;
	}

	/*
	 * gets the url from the user and returns the html content of the requested page
	 * as a string
	 */
	@RequestMapping(path = "/searchProduct", method = RequestMethod.GET)
	public String renderPage(@RequestParam String url) {
		return renderPageService.renderPage(url);
	}

	/*
	 * returns a list of all product names in the descending order of their demand
	 */
	@RequestMapping(path = "/mostSearched", method = RequestMethod.GET)
	public List<String> getMostSearchedProducts() {
		return mostSearchedProductService.getMostSearchedProducts();
	}

	@RequestMapping(path = "/getProduct/{pid}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable Integer pid) {
		return productRepository.findByProductId(pid);
	}

	@RequestMapping(path = "/getProducts", method = RequestMethod.GET)
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

}
