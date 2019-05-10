package org.crownpeak.api;

public class APIData {

	public String host;
	public String instance;
	public String apiKey;
	public Boolean authenticated;
	public String cookie;
	public final String webAPIRoot = "/cpt_webservice/accessapi/";
	
	public APIData() {
		this.host ="";
		this.instance = "";
		this.apiKey = "";
		this.authenticated = false;
		this.cookie = "";
	}
	
	public Boolean isAuthenticated() {
		return this.authenticated;
	}
	
}
