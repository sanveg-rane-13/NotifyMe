package com.app.notifyme.services;

import com.app.notifyme.models.Product;
import com.app.notifyme.models.User;

public interface EmailService {
	
	void setSender(String senderEmail);
	boolean sendEmail(User user, Product product);
}
