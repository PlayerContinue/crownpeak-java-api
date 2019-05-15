package org.crownpeak.api.request;

public enum OrderType {
	
	
	ASCENDING ("Ascending"), 	

	DESCENDING ("Descending"),	

	NOTSET ("NotSet"),	

	SAVED ("Saved");
	
	private String orderType;
	
	public String value() {
		return this.orderType;
	}
	
	private OrderType(String orderType) {
		this.orderType = orderType;
	}
	
}
