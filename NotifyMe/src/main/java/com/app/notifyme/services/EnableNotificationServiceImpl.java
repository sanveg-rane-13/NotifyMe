package com.app.notifyme.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.notifyme.models.Product;
import com.app.notifyme.models.Usercriteria;
import com.app.notifyme.repositories.ProductRepository;
import com.app.notifyme.repositories.UserCriteriaRepository;

@Service("enableNotificationService")
public class EnableNotificationServiceImpl implements EnableNotificationService {

	@Autowired
	UserCriteriaRepository userCriteriaRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Override
	@Transactional
	public void enableNotification(Integer id) {
		// TODO Auto-generated method stub
		Usercriteria usercriteria=userCriteriaRepository.findByTrackId(id);
		usercriteria.setNotifiedDate(null);
		Product product=usercriteria.getProduct();
		int track_count=product.getTrackCount();
		product.setTrackCount(track_count+1);
		userCriteriaRepository.saveAndFlush(usercriteria);
        productRepository.saveAndFlush(product);
	}

}
