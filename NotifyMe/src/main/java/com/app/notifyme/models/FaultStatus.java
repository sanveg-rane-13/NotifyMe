package com.app.notifyme.models;


public enum FaultStatus {
	
	NORMAL(0),
	URL_BROKEN(1),
	XPATH_BROKEN(2);
	
	private int faultStatus;
	
	 private FaultStatus(int status) {
		// TODO Auto-generated constructor stub
		 faultStatus=status;
	}
	 
	  public int getFaultStatus() {
	      return faultStatus;
	   } 

	
	
	

}
