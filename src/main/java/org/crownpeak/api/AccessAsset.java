package org.crownpeak.api;

import org.crownpeak.api.request.AssetCreateRequest;
import org.crownpeak.api.request.ExistsRequest;
import org.crownpeak.api.response.AssetCreateResponse;
import org.crownpeak.api.response.AssetExistsResponse;

public class AccessAsset {

	public API api;
	
	public AccessAsset(API api) {
		this.api = api;
	}
	
	/**
	 * Create a new asset in the cms
	 * @param request - The request containing information about the asset
	 * @return
	 */
	public AssetCreateResponse create(AssetCreateRequest request) {
		return MakeRequest.makeRequest("/Asset/Create/", request, api,AssetCreateResponse.class);
	}
	
	/**
	 * 
	 * @param request - The request object containing the path/id to check
	 * @return - requestResponse
	 */
	public AssetExistsResponse exists(ExistsRequest request) {
		return MakeRequest.makeRequest("/Asset/Exists/", request, this.api,AssetExistsResponse.class);
	}
	
	/**
	 * 
	 * @param id - The id of the asset to check if it exists
	 * @return - requestResponse 
	 */
	public AssetExistsResponse exists(Integer id) {
		return exists(new ExistsRequest(id));
	}
	
	/**
	 * 
	 * @param path - The path of the asset id to check
	 * @return - requestResponse
	 */
	public AssetExistsResponse exists(String path) {
		return exists(new ExistsRequest(path));
	}
	
	
	
	
	
}
