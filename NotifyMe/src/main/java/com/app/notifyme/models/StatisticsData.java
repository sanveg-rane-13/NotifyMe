package com.app.notifyme.models;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class StatisticsData {
	private double price;
	private String productName;
	private String time = null;
	private String date = null;

	private SimpleDateFormat dateFormater;

	public StatisticsData(double price, Time time, String productName) {
		super();
		this.price = price;
		this.productName = productName;

		dateFormater = new SimpleDateFormat("HH-mm");
		this.time = dateFormater.format(time);
	}

	public StatisticsData(double price, Date date, String productName) {
		super();
		this.price = price;
		this.productName = productName;

		dateFormater = new SimpleDateFormat("dd-MM-yy");
		this.date = dateFormater.format(date);
	}

	public String getTime() {
		return time;
	}

	public String getDate() {
		return date;
	}

	public double getPrice() {
		return price;
	}
	
	public String getProductName() {
		return productName;
	}
}
