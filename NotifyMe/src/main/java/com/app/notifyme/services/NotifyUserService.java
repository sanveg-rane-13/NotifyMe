package com.app.notifyme.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;

import com.app.notifyme.models.Product;
import com.app.notifyme.models.User;
import com.app.notifyme.models.Usercriteria;
import com.app.notifyme.repositories.UserCriteriaRepository;

@Component
public class NotifyUserService {

	@Autowired
	UserCriteriaRepository userCriteriaRepository;

	final String sender = "sanveg.rane@somaiya.edu";

	final static Logger logger = Logger.getLogger(NotifyUserService.class);

	boolean emailUser(User user, Product product) {
		logger.info("Sending email to user: " + user.getName());

		String recipient = user.getEmail();
		String host = "127.0.0.1";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);

		try {
			System.out.println("Trying mail");
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(sender));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("Product notification");
			message.setText("Product " + product.getProductName() + " matched criteria. Use URL: " + product.getUrl());

			Transport.send(message);
			System.out.println("Mail successfully sent");
		} catch (MessagingException mex) {
			System.out.println("Failed to mail");
			mex.printStackTrace();
			return false;
		}
		return true;
	}

	public void checkCriteriaMatch(int productId, double price) {
		List<Usercriteria> userCriteriaList = userCriteriaRepository.getCriteria(productId);

		if (userCriteriaList != null) {
			for (Usercriteria userCriteria : userCriteriaList) {
				if (userCriteria.getNotifiedDate() == null) {
					if (price <= userCriteria.getCriteria()) {
						boolean result = emailUser(userCriteria.getUser(), userCriteria.getProduct());
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
