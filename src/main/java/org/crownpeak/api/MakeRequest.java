package org.crownpeak.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public final class MakeRequest {
	
	/**
	 * make a asynchrounous request to the API based on data provided
	 * @param url - The full url to make the call to
	 * @param request - Object containing the parameters of the request to make
	 * @param apiData - The data of the current api
	 * @throws IOException
	 */
	public static final void makeRequest(String urlPath, APIRequest request, APIData apiData) {
		MakeRequest.makeRequest("https://"+ apiData.host + "/" + apiData.instance + "/" + apiData.webAPIRoot + urlPath, request,apiData);
	}
	
	/**
	 * make a synchronous request to the API based on data provided
	 * @param url - The full url to make the call to
	 * @param request - Object containing the parameters of the request to make
	 * @param apiData - The data of the current api
	 * @throws IOException
	 */
	public static final void makeRequestSync(String urlPath, APIRequest request, APIData apiData) {
		MakeRequest.makeRequestSync("https://"+ apiData.host + "/" + apiData.instance + "/" + apiData.webAPIRoot + urlPath, request,apiData);
	}
	
	
	/**
	 * make a synchronous request to the API based on data provided
	 * @param url - The full url to make the call to
	 * @param request - Object containing the parameters of the request to make
	 * @param apiData - The data of the current api
	 * @throws IOException
	 */
	public static final String makeRequestSync(URL url, APIRequest request, APIData apiData) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("x-api-key", apiData.apiKey);
		connection.setRequestProperty("accept", "application/json");
		connection.setRequestProperty("Content-Type", "text/json");
		//If it's already authenticated, include the key
		if(apiData.isAuthenticated) {
			connection.setRequestProperty("cookie", apiData.cookie.toString());
		}
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(request.toJSON());
		wr.close();
		
		//Get Response  
	    InputStream is = connection.getInputStream();
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	    StringBuffer response = new StringBuffer(); 
	    String line;
	    while ((line = rd.readLine()) != null) {
	      response.append(line);
	      response.append('\r');
	    }
	    rd.close();
	    return response.toString();
		
		
	}
}



