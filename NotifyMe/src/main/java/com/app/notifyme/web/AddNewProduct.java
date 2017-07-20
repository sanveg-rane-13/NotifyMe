package com.app.notifyme.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.notifyme.models.Product;
import com.app.notifyme.repositories.ProductRepository;
import com.app.notifyme.services.SchedulerService;

/*
 * Service to add a new product in the products table.
 * The service checks whether the product with same exists in the table.
 * If no product exists then new product is added to the table in order to track price.
 * If product exists then the track count is updated.
 */

@RestController
public class AddNewProduct {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SchedulerService schedulerService;

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public void addProduct(@RequestBody Product product) {
		String url = product.getUrl();
		// System.out.println(product.get);
		// Check if product with same url exists
		Product existingProd = productRepository.findProduct(url);

		if (existingProd != null) {
			int count = existingProd.getTrackCount();
			existingProd.setTrackCount(count + 1);
			System.out.println(
					"added product count: " + existingProd.getProductName() + " -> " + existingProd.getTrackCount());
			productRepository.save(existingProd);
		} else {
			System.out.println("added product");
			productRepository.save(product);
		}
		schedulerService.updateProductList();
	}

}
