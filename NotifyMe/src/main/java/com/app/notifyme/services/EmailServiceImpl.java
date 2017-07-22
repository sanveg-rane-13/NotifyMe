package com.app.notifyme.services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.app.notifyme.models.Product;
import com.app.notifyme.models.User;

public class EmailServiceImpl implements EmailService {

	private final static Logger logger = Logger.getLogger(EmailServiceImpl.class);

	private String sender;
	private String password;

	@Override
	public void setSenderPassword(String senderEmail, String password) {
		this.sender = senderEmail;
		this.password = password;
	}

	@Override
	public boolean sendEmail(User user, Product product) {

		logger.info("Sending email to user: " + user.getName());
		logger.info("Email sender: " + this.sender);
		logger.info("Email receiver: " + user.getEmail());

		// Step 1 - Creating session object
		String recipient = user.getEmail();
		// String host = "smtp.journaldev.com";
		Properties props = System.getProperties();
		// properties.setProperty("mail.smtp.host", host);

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(sender, password);
					}
				  });

		try {
			System.out.println("Trying mail");

			// Step 2 - Creating MimeMessage object
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(sender));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("Product notification");
			message.setText("Product " + product.getProductName() + " matched criteria. Use URL: " + product.getUrl());

			// Step 3 - Send the email
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
