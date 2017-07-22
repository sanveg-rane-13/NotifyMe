package com.app.notifyme.web;

import java.sql.Date;
import java.time.LocalDate;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.notifyme.models.CriteriaData;
import com.app.notifyme.models.Product;
import com.app.notifyme.models.User;
import com.app.notifyme.models.Usercriteria;
import com.app.notifyme.repositories.ProductRepository;
import com.app.notifyme.repositories.UserCriteriaRepository;
import com.app.notifyme.repositories.UserRepository;

@RestController
public class UserCriteriaController {
	
	final static Logger logger = Logger.getLogger(ProductController.class);
	
	@Autowired
	UserCriteriaRepository userCriteriaRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductController productController;
	
	@RequestMapping(path = "/addCriteria", method = RequestMethod.POST)
	public void addNewCriteria(@RequestBody CriteriaData criteriaData) {
		String userEmail = criteriaData.getUserEmail();
		User user = userRepository.getUserById(userEmail);
		logger.info("Inserting criteria for user: " + user + " email: " + userEmail);
		
		if(user != null) {
			Usercriteria userCriteria = new Usercriteria();
			
			String url = criteriaData.getUrl();
			String xPath = criteriaData.getxPath();
			String productName = criteriaData.getProductName();
			double currentPrice = criteriaData.getCurrentPrice();
			
			Product product = new Product();
			product.setProductName(productName);
			product.setUrl(url);
			product.setXPath(xPath);
			product.setCurrentPrice(currentPrice);
			
			int productId = productController.addProduct(product);
			logger.info("Inserting criteria product: " + productId);
			
			if(productId != -1) {
				userCriteria.setCriteria(criteriaData.getUserCriteria());
				userCriteria.setDate(Date.valueOf(LocalDate.now()));
				userCriteria.setFinalPrice(0);
				userCriteria.setStartPrice(criteriaData.getCurrentPrice());
				userCriteria.setProduct(productRepository.findByProductId(productId));
				userCriteria.setUser(user);
				
				userCriteriaRepository.save(userCriteria);
				logger.info("Inserted criteria");
			}
		} else {
			logger.error("Criteria insert failed");
		}
		
	}
}
