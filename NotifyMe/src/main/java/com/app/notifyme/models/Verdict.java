package com.app.notifyme.models;

public enum Verdict {
	
	UNRESOLVED(0),
	URL_FIXED(1),
	XPATH_FIXED(2),
	OUT_OF_STOCK(3);
	
	private int verdictStatus;
	
	
	  Verdict(int status) {
	      verdictStatus = status;
	   }
	
	  public int getVerdictStatus() {
	      return verdictStatus;
	   } 

}
