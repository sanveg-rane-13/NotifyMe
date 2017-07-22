package com.app.notifyme.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.notifyme.repositories.ErrorRepository;
import com.app.notifyme.repositories.ProductRepository;
import java.util.List;

import javax.transaction.Transactional;

import com.app.notifyme.models.Error;
import com.app.notifyme.models.ErrorResponse;
import com.app.notifyme.models.FaultStatus;
import com.app.notifyme.models.Product;
import com.app.notifyme.models.User;
import com.app.notifyme.models.Usercriteria;
import com.app.notifyme.models.Verdict;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	final static Logger logger = Logger.getLogger(AdminServiceImpl.class);

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ErrorRepository errorRepository;

	@Override
	@Transactional
	public void fixFaulty(ErrorResponse errorResponse) {
		Error error = errorRepository.findById(errorResponse.getId());
		error.setVerdict(errorResponse.getVerdict());
		logger.error(error);
		errorRepository.saveAndFlush(error);
		Product p = error.getProduct();
		String message = "";
		if (error.getVerdict() == Verdict.URL_FIXED.getVerdictStatus()) {

			/*
			 * * update the url and xpath in products table notify users by mail
			 */

			p.setUrl(errorResponse.getNewUrl());
			p.setXPath(errorResponse.getNewXpath());
			p.setFaultStatus(FaultStatus.NORMAL.getFaultStatus());
			message = "The url has been fixed to " + p.getUrl();

		}

		else if (error.getVerdict() == Verdict.XPATH_FIXED.getVerdictStatus()) {
			/*
			 * xpath has been fixed update the xpath in products table update faultstatus to
			 * 0 notify to users
			 */

			p.setXPath(errorResponse.getNewXpath());
			p.setFaultStatus(FaultStatus.NORMAL.getFaultStatus());
			message = "The xpath has been fixed to " + p.getXPath();

		}

		else if (error.getVerdict() == Verdict.OUT_OF_STOCK.getVerdictStatus()) {
			message = "The product is out of stock. Wait till it comes in stock again";
		}

		List<Usercriteria> criteria_list = p.getUsercriterias();
		if (criteria_list == null) {
			System.out.println("no user criteria loaded");
		}
		for (Usercriteria criteria : criteria_list) {
			User user = criteria.getUser();
			logger.info("sent mail to " + user + "with message " + message);
		}
		productRepository.saveAndFlush(p);

	}

}
