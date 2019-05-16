package org.crownpeak.api;

import org.crownpeak.api.request.APIRequest;
import org.crownpeak.api.request.AssetAttachRequest;
import org.crownpeak.api.request.AssetCreateRequest;
import org.crownpeak.api.request.AssetDeleteRequest;
import org.crownpeak.api.request.AssetExecuteWorkflowCommandRequest;
import org.crownpeak.api.request.AssetMoveRequest;
import org.crownpeak.api.request.AssetPagedRequest;
import org.crownpeak.api.request.AssetPublishRefreshRequest;
import org.crownpeak.api.request.AssetPublishRequest;
import org.crownpeak.api.request.AssetRenameRequest;
import org.crownpeak.api.request.AssetRouteRequest;
import org.crownpeak.api.request.AssetUpdateRequest;
import org.crownpeak.api.request.AssetUploadRequest;
import org.crownpeak.api.request.ExistsRequest;
import org.crownpeak.api.response.AssetAttachResponse;
import org.crownpeak.api.response.AssetBranchResponse;
import org.crownpeak.api.response.AssetCreateResponse;
import org.crownpeak.api.response.AssetDeleteResponse;
import org.crownpeak.api.response.AssetExecuteWorkflowCommandResponse;
import org.crownpeak.api.response.AssetExistsResponse;
import org.crownpeak.api.response.AssetFieldsResponse;
import org.crownpeak.api.response.AssetMoveResponse;
import org.crownpeak.api.response.AssetPagedResponse;
import org.crownpeak.api.response.AssetPublishRefreshResponse;
import org.crownpeak.api.response.AssetPublishResponse;
import org.crownpeak.api.response.AssetReadResponse;
import org.crownpeak.api.response.AssetRenameResponse;
import org.crownpeak.api.response.AssetRouteResponse;
import org.crownpeak.api.response.AssetUndeleteResponse;
import org.crownpeak.api.response.AssetUpdateResponse;
import org.crownpeak.api.response.AssetUploadResponse;

public class AccessAsset {

	public API api;
	
	/**
	 * Create an new instance of the object
	 * @param api - An authenticated api
	 * @throws Exception - Throws error when API is not authenticated
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
	 * @return - The response from the cms
	 */
	public AssetAttachResponse attach(AssetAttachRequest request) {
		return MakeRequest.makeRequest("/Asset/Attach/", request, api,AssetAttachResponse.class);
	}
	
	/**
	 * Branch an asset in the cms
	 * @param id - The Id of the asset to branch
	 * @return - The response from the cms
	 */
	public AssetBranchResponse branch(int id) {
		return MakeRequest.makeRequest("/Asset/Branch/" + id, new APIRequest(), api,AssetBranchResponse.class);
	}
	
	/**
	 * Create a new asset in the cms
	 * @param request - The request containing information about the asset
	 * @return - The response from the cms
	 */
	public AssetCreateResponse create(AssetCreateRequest request) {
		return MakeRequest.makeRequest("/Asset/Create/", request, api,AssetCreateResponse.class);
	}
	
	/**
	 * Delete a requested asset in the cms by id
	 * @param id - The id of the asset to be deleted
	 * @return - The response from the cms
	 */
	public AssetDeleteResponse delete(int id) {
		return MakeRequest.makeRequest("/Asset/Delete/" + id, new AssetDeleteRequest(), api,AssetDeleteResponse.class);
	}
	
	/**
	 * Move an asset through workflow
	 * @param request - The request contaiing information on what to move through workflow
	 * @return - The response from the cms
	 */
	public AssetExecuteWorkflowCommandResponse executeWorkflowCommand(AssetExecuteWorkflowCommandRequest request) {
		return MakeRequest.makeRequest("/Asset/ExecuteWorkflowCommand/", request, api, AssetExecuteWorkflowCommandResponse.class);
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
	
	/**
	 * Read all fields from an asset
	 * @param id - The id of the asset to read
	 * @return - The response from the cms
	 */
	public AssetFieldsResponse fields(int id) {
		return MakeRequest.makeRequest("/Asset/Fields/" + id, new APIRequest(), api,AssetFieldsResponse.class);
	}
	
	/**
	 * Move an asset from one folder to another
	 * @param request - The request to move the asset
	 * @return - The response from the cms
	 */
	public AssetMoveResponse move(AssetMoveRequest request) {
		return MakeRequest.makeRequest("/Asset/Move/",request, api, AssetMoveResponse.class);
	}
	
	/**
	 * Get a list of assets in a folder
	 * @param request - The request containing the information desired from the folder
	 * @return - The response from the cms
	 */
	public AssetPagedResponse paged(AssetPagedRequest request) {
		return MakeRequest.makeRequest("/Asset/Paged/", request, api,AssetPagedResponse.class);
	}
	
	/**
	 * Publish an asset without any workflow
	 * @param request - The request to publish
	 * @return - The response from the cms
	 */
	public AssetPublishResponse publish(AssetPublishRequest request) {
		return MakeRequest.makeRequest("/Asset/Publish/", request, api,AssetPublishResponse.class);
	}
	
	public AssetPublishRefreshResponse publishRefresh(AssetPublishRefreshRequest request) {
		return MakeRequest.makeRequest("/Asset/PublishRefresh/", request, api,AssetPublishRefreshResponse.class);
	}
	
	/**
	 * Get information about an asset by id
	 * @param id - The id of the asset to get information about
	 * @return - The response from the cms
	 */
	public AssetReadResponse read(int id) {
		return MakeRequest.makeRequest("/Asset/Read/" + id,new  APIRequest(), api,AssetReadResponse.class);
	}
	
	/**
	 * Rename an asset
	 * @param request - The request containing information to rename the asset
	 * @return - The response from the cms
	 */
	public AssetRenameResponse rename(AssetRenameRequest request) {
		return MakeRequest.makeRequest("/Asset/Rename/", request, api,AssetRenameResponse.class);
	}
	
	/**
	 * Rename an Asset
	 * @param assetId - The id of the asset to rename
	 * @param newName - The desired new name of the asset
	 * @return - The response from the cms
	 */
	public AssetRenameResponse rename(int assetId, String newName) {
		return rename(new AssetRenameRequest(assetId, newName));
	}

	/**
	 * Route an asset to a different state
	 * @param request - The request containing information on routing the asset
	 * @return - The response from the cms
	 */
	public AssetRouteResponse route(AssetRouteRequest request) {
		return MakeRequest.makeRequest("/Asset/Route", request, api,AssetRouteResponse.class);
	}
	
	/**
	 * Route an asset to a different state
	 * @param assetId -  Id of asset to route
	 * @param stateId - Id of the state to route to
	 * @return - The response from the server
	 */
	public AssetRouteResponse route(int assetId, int stateId) {
		return route(new AssetRouteRequest(assetId,stateId));
	}
	
	/**
	 * Undelete an asset by id
	 * @param id - The id of the asset to undelete
	 * @return - Response
	 */
	public AssetUndeleteResponse undelete(int id) {
		return MakeRequest.makeRequest("/Asset/Undelete/" + id, new APIRequest(), api,AssetUndeleteResponse.class);
	}
	
	/**
	 * Update the fields of an asset
	 * @param request - The request containing what do delete and add to the asset
	 * @return - The response from the cms
	 */
	public AssetUpdateResponse update(AssetUpdateRequest request) {
		return MakeRequest.makeRequest("/Asset/Update", request, api, AssetUpdateResponse.class);
	}
	
	/**
	 * Upload a binary file to the cms
	 * @param request - The request to upload the asset
	 * @return - The response from the cms
	 */
	public AssetUploadResponse upload(AssetUploadRequest request) {
		return MakeRequest.makeRequest("/Asset/Upload", request, api,AssetUploadResponse.class);
	}
	
	
	
	
	
}
