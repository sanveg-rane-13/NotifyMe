package com.app.notifyme.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import com.app.notifyme.models.Usercriteria;
import com.app.notifyme.repositories.UserCriteriaRepository;

@Component
public class NotifyUserService {

	@Autowired
	UserCriteriaRepository userCriteriaRepository;

	final String sender = "sanveg.rane@somaiya.edu";

	final static Logger logger = Logger.getLogger(NotifyUserService.class);

	public void checkCriteriaMatch(int productId, double price) {
		List<Usercriteria> userCriteriaList = userCriteriaRepository.getCriteria(productId);

		if (userCriteriaList != null) {
			for (Usercriteria userCriteria : userCriteriaList) {
				if (userCriteria.getNotifiedDate() == null) {
					if (price <= userCriteria.getCriteria()) {

						logger.info("Sending email");
						EmailService emailService = new EmailServiceImpl();
						emailService.setSender("srsanrocks1@gmail.com");
						boolean result = emailService.sendEmail(userCriteria.getUser(), userCriteria.getProduct());

						if (result) {
							System.out.println("Updating criteria");
							userCriteria.setNotifiedDate(Date.valueOf(LocalDate.now()));
							userCriteria.setFinalPrice(price);
							userCriteriaRepository.save(userCriteria);
						}
					}
				}
			}
		}
	}
}
