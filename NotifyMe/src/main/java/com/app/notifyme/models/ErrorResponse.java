package com.app.notifyme.models;

public class ErrorResponse {

	int id;
	int errorType;
	int verdict;
	String productName;
	String newUrl;
	String newXpath;

	public int getVerdict() {
		return verdict;
	}

	public void setVerdict(int verdict) {
		this.verdict = verdict;
	}

	public ErrorResponse() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getErrorType() {
		return errorType;
	}

	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getNewUrl() {
		return newUrl;
	}

	public void setNewUrl(String newUrl) {
		this.newUrl = newUrl;
	}

	public String getNewXpath() {
		return newXpath;
	}

	public void setNewXpath(String newXpath) {
		this.newXpath = newXpath;
	}

}
