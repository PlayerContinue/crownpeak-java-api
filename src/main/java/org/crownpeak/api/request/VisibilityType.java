package org.crownpeak.api.request;


public enum VisibilityType {

	 DELETED ("Deleted"),
	HIDDEN  ("Hidden"),
	NORMAL ("Normal");
	 
	 
	 private String visibilityType;
	 
	 public String value() {
		 return this.visibilityType;
	 }
	 
	 private VisibilityType(String visibilityType) {
		 this.visibilityType = visibilityType;
	 }
	
}
