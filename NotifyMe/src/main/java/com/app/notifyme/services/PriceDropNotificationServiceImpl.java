package com.app.notifyme.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.notifyme.models.Product;
import com.app.notifyme.models.User;
import com.app.notifyme.models.Usercriteria;
import com.app.notifyme.repositories.ProductRepository;
import com.app.notifyme.repositories.UserCriteriaRepository;
import com.app.notifyme.repositories.UserRepository;

@Service("priceDropNotificationService")
public class PriceDropNotificationServiceImpl implements PriceDropNotificationService {
	
	@Autowired
	UserCriteriaRepository userCriteriaRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;

	@Override
	@Transactional
	public void notifyPriceDrop(List<Usercriteria> userCriteriaList,double price) {
		// TODO Auto-generated method stub
		for(Usercriteria user_criteria:userCriteriaList){
			user_criteria.setFinalPrice(price);
			user_criteria.setNotifiedDate(Date.valueOf(LocalDate.now()));
			userCriteriaRepository.saveAndFlush(user_criteria);
			Product product=user_criteria.getProduct();
			int trackCount=product.getTrackCount();
			product.setTrackCount(trackCount-1);
			productRepository.saveAndFlush(product);
			User user=user_criteria.getUser();
			System.out.println("sent mail to user "+user+ "with a price drop from "+user_criteria.getStartPrice()+" to a drop of "+user_criteria.getFinalPrice());
		}

	}

}
