package org.crownpeak.api;

import org.crownpeak.api.request.AssetLogRequest;
import org.crownpeak.api.response.AssetLogResponse;

public class AccessUtil {
	private API api;
	
	@SuppressWarnings("unused")
	private AccessUtil() {
		
	}
	
	/**
	 * 
	 * @param api - The api to be utilized
	 * @throws Exception  - Throws error when API is not authenticated
	 */
	public AccessUtil(API api) throws Exception {
		if(!api.isAuthenticated()) {
			throw new Exception("API is not authenticated. No functions can be run.");
		}
		this.api = api;
		
	}
	
	/**
	 * Log a message to the cms
	 * @param assetId - The assetid of the asset to attach the message to 
	 * @param message - The message to add to the cms
	 * @return - A response from the cms
	 */
	public AssetLogResponse log(Integer assetId, String message) {
		return MakeRequest.makeRequest("/Util/Log/", new AssetLogRequest(assetId,message), api,AssetLogResponse.class);
	}
	
	/**
	 * Log a message to the cms
	 * @param message - The assetid of the asset to attach the message to 
	 * @return - A response from the cms
	 */
	public AssetLogResponse log(String message) {
		return log(null,message);
	}
	
}
