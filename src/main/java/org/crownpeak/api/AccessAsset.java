package org.crownpeak.api;

import org.crownpeak.api.request.APIRequest;
import org.crownpeak.api.request.AssetCreateRequest;
import org.crownpeak.api.request.AssetDeleteRequest;
import org.crownpeak.api.request.AssetUpdateRequest;
import org.crownpeak.api.request.ExistsRequest;
import org.crownpeak.api.response.AssetBranchResponse;
import org.crownpeak.api.response.AssetCreateResponse;
import org.crownpeak.api.response.AssetDeleteResponse;
import org.crownpeak.api.response.AssetExistsResponse;
import org.crownpeak.api.response.AssetReadResponse;
import org.crownpeak.api.response.AssetUpdateResponse;

public class AccessAsset {

	public API api;
	
	/**
	 * Create an new instance of the object
	 * @param api - An authenticated api
	 * @throws Exception 
	 */
	public AccessAsset(API api) throws Exception {
		if(!api.isAuthenticated()) {
			throw new Exception("API is not authenticated. No functions can be run.");
		}
		this.api = api;
	}
	
	public AssetBranchResponse branch(int id) {
		return MakeRequest.makeRequest("/Asset/Branch/", new APIRequest(), api,AssetBranchResponse.class);
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
	 * Delete a requested asset in the cms by id
	 * @param id - The id of the asset to be deleted
	 * @return 
	 */
	public AssetDeleteResponse delete(int id) {
		return MakeRequest.makeRequest("/Asset/Delete/" + id, new AssetDeleteRequest(), api,AssetDeleteResponse.class);
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
	
	public AssetReadResponse read(int id) {
		return MakeRequest.makeRequest("/Asset/Fields/" + id, new APIRequest(), api,AssetReadResponse.class);
	}
	
	public AssetUpdateResponse update(AssetUpdateRequest request) {
		return MakeRequest.makeRequest("/Asset/Update", request, api, AssetUpdateResponse.class);
	}
	
	
	
	
	
}
