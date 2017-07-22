package com.app.notifyme.models;

public class CriteriaData {

	private String userEmail;
	private String url;
	private String xPath;
	private String productName;
	private double currentPrice;
	private double userCriteria;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getxPath() {
		return xPath;
	}

	public void setxPath(String xPath) {
		this.xPath = xPath;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public double getUserCriteria() {
		return userCriteria;
	}

	public void setUserCriteria(double userCriteria) {
		this.userCriteria = userCriteria;
	}
}
