package org.crownpeak.api;

import org.crownpeak.api.request.AssetLogRequest;
import org.crownpeak.api.response.AssetLogResponse;

public class AccessUtil {
	private API api;
	
	@SuppressWarnings("unused")
	private AccessUtil() {
		
	}
	
	public AccessUtil(API api) throws Exception {
		if(!api.isAuthenticated()) {
			throw new Exception("API is not authenticated. No functions can be run.");
		}
		this.api = api;
		
	}
	
	public AssetLogResponse log(Integer assetId, String message) {
		return MakeRequest.makeRequest("/Util/Log/", new AssetLogRequest(assetId,message), api,AssetLogResponse.class);
	}
	
	public AssetLogResponse log(String message) {
		return log(null,message);
	}
	
}
