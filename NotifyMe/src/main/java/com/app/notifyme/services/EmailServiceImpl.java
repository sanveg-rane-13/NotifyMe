package com.app.notifyme.services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.app.notifyme.models.Product;
import com.app.notifyme.models.User;

public class EmailServiceImpl implements EmailService{
	
	private final static Logger logger = Logger.getLogger(EmailServiceImpl.class);
	
	private String sender;
	
	@Override
	public void setSender(String senderEmail) {
		this.sender = senderEmail;
	}

	@Override
	public boolean sendEmail(User user, Product product) {
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

	

}
