package org.crownpeak.api;

import org.crownpeak.api.request.ExistsRequest;
import org.crownpeak.api.response.ExistsResponse;

public class AccessAsset {

	public API api;
	
	public AccessAsset(API api) {
		this.api = api;
	}
	
	/**
	 * 
	 * @param request - The request object containing the path/id to check
	 * @return - requestResponse
	 */
	public ExistsResponse Exists(ExistsRequest request) {
		return MakeRequest.makeRequest("/Asset/Exists/", request, this.api,ExistsResponse.class);
	}
	
	/**
	 * 
	 * @param id - The id of the asset to check if it exists
	 * @return - requestResponse 
	 */
	public ExistsResponse Exists(Integer id) {
		return Exists(new ExistsRequest(id));
	}
	
	/**
	 * 
	 * @param path - The path of the asset id to check
	 * @return - requestResponse
	 */
	public ExistsResponse Exists(String path) {
		return Exists(new ExistsRequest(path));
	}
	
}
