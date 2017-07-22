package com.app.notifyme.services;

import com.app.notifyme.models.FaultStatus;
import com.app.notifyme.models.Product;

public interface ErrorNotificationService {
	
	void notifyError(Product product,FaultStatus faultStatus);
}
