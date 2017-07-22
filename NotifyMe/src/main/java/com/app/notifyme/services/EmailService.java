package com.app.notifyme.services;

import com.app.notifyme.models.Product;
import com.app.notifyme.models.User;

public interface EmailService {
	
	boolean sendEmail(User user, Product product);
	void setSenderPassword(String senderEmail, String password);
}
