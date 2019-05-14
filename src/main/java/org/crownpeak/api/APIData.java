package org.crownpeak.api;

import org.apache.http.impl.client.CloseableHttpClient;

public class APIData {

	public String host;
	public String instance;
	public String apiKey;
	public Boolean authenticated;
	public String cookie;
	public final String webAPIRoot = "/cpt_webservice/accessapi/";
	public CloseableHttpClient client;
	
	public APIData() {
		this.host ="";
		this.instance = "";
		this.apiKey = "";
		this.authenticated = false;
		this.cookie = "";
		this.client = null;
	}
	
	public Boolean isAuthenticated() {
		return this.authenticated;
	}
	
}
