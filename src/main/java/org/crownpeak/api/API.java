package org.crownpeak.api;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.crownpeak.api.request.AuthenticateRequest;

/**
 * API Class required to perform any actions
 * @author dgree
 *
 */
public class API extends APIData{

	/**
	 * 
	 * @param host - The url of the cms exclusing the instance name (usually cms.crownpeak.net)
	 * @param instance - The instance name (Sandbox5 for example)
	 * @param apiKey - Your api key
	 */
	public API(String host, String instance, String apiKey) {
		this.apiKey = apiKey;
		this.host = host;
		this.instance = instance;
	}
	
	
	public boolean login(String username, String password) {
		AuthenticateRequest request = new AuthenticateRequest(username,password,this.instance);
		try {
			HttpResponse response = MakeRequest.makeRequest("https://"+ this.host + "/" + this.instance + 
					"/" + this.webAPIRoot + MakeRequest.modifyURLPath("/Auth/Authenticate"), request, this);
			if(response.getStatusLine().getStatusCode() == Util.StatusCode.SUCCESS.value()) {
				this.authenticated = true;
			}else {
				//TODO Throw an error of some sort
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	

	
}





