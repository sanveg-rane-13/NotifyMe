package com.app.notifyme.services;

import java.util.List;

import com.app.notifyme.models.Usercriteria;

public interface PriceDropNotificationService {
   
	void notifyPriceDrop(List<Usercriteria> criteriaList,double price);
}
