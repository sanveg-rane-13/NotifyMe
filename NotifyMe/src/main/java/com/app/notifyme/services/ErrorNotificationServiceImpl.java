package com.app.notifyme.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.notifyme.models.Product;
import com.app.notifyme.models.Verdict;
import com.app.notifyme.models.Error;
import com.app.notifyme.models.FaultStatus;
import com.app.notifyme.repositories.ErrorRepository;
import com.app.notifyme.repositories.ProductRepository;

@Service("errorNotificationService")
public class ErrorNotificationServiceImpl implements ErrorNotificationService{
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ErrorRepository errorRepository;

	
	@Override
	public void notifyError(Product product, FaultStatus faultStatus) {
		// TODO Auto-generated method stub
		Error error=new Error();
		if(faultStatus==FaultStatus.URL_BROKEN){
			product.setFaultStatus(FaultStatus.URL_BROKEN.getFaultStatus());
			error.setErrorType(FaultStatus.URL_BROKEN.getFaultStatus());
		}
		else if(faultStatus==FaultStatus.XPATH_BROKEN){
			product.setFaultStatus(FaultStatus.XPATH_BROKEN.getFaultStatus());
			error.setErrorType(FaultStatus.XPATH_BROKEN.getFaultStatus());
		}
		productRepository.saveAndFlush(product);
		error.setOldUrl(product.getUrl());
		error.setProduct(product);
		error.setVerdict(Verdict.UNRESOLVED.getVerdictStatus());
		errorRepository.saveAndFlush(error);
		
		
		
	}
    
}
