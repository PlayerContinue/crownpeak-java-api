package org.crownpeak.api;

/**
 * API Class required to perform any actions
 * @author dgree
 *
 */
public class API extends APIData{

	public API(String host, String instance, String apiKey) {
		this.apiKey = apiKey;
		this.host = host;
		this.instance = instance;
	}
	
	public boolean login(String username, String password) {
		AuthenticateRequest request = new AuthenticateRequest(username,password);
		MakeRequest.makeRequest("/Auth/Authenticate", request, this);
		return true;
	}
	
	
}





