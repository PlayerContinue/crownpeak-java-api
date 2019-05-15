package org.crownpeak.api;

import org.crownpeak.api.request.APIRequest;
import org.crownpeak.api.request.AssetAttachRequest;
import org.crownpeak.api.request.AssetCreateRequest;
import org.crownpeak.api.request.AssetDeleteRequest;
import org.crownpeak.api.request.AssetMoveRequest;
import org.crownpeak.api.request.AssetUpdateRequest;
import org.crownpeak.api.request.ExistsRequest;
import org.crownpeak.api.response.AssetAttachResponse;
import org.crownpeak.api.response.AssetBranchResponse;
import org.crownpeak.api.response.AssetCreateResponse;
import org.crownpeak.api.response.AssetDeleteResponse;
import org.crownpeak.api.response.AssetExistsResponse;
import org.crownpeak.api.response.AssetMoveResponse;
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
	
	/**
	 * Attach an binary file to an asset
	 * @param request - The request to attach an asset
	 * @return
	 */
	public AssetAttachResponse Attach(AssetAttachRequest request) {
		return MakeRequest.makeRequest("/Asset/Attach/", request, api,AssetAttachResponse.class);
	}
	
	/**
	 * Branch an asset in the cms
	 * @param id - The Id of the asset to branch
	 * @return
	 */
	public AssetBranchResponse branch(int id) {
		return MakeRequest.makeRequest("/Asset/Branch/" + id, new APIRequest(), api,AssetBranchResponse.class);
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
	
	public AssetMoveResponse move(AssetMoveRequest request) {
		return MakeRequest.makeRequest("/Asset/Move/",request, api, AssetMoveResponse.class);
	}
	
	/**
	 * Read all fields from an asset
	 * @param id - The id of the asset to read
	 * @return
	 */
	public AssetReadResponse read(int id) {
		return MakeRequest.makeRequest("/Asset/Fields/" + id, new APIRequest(), api,AssetReadResponse.class);
	}
	
	/**
	 * Update the fields of an asset
	 * @param request - The request containing what do delete and add to the asset
	 * @return
	 */
	public AssetUpdateResponse update(AssetUpdateRequest request) {
		return MakeRequest.makeRequest("/Asset/Update", request, api, AssetUpdateResponse.class);
	}
	
	
	
	
	
}
