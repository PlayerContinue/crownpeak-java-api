package org.crownpeak.api.request;

public class ExistsRequest extends APIRequest {

	/**
	 * Asset id or path to check exists
	 */
	public String assetIdOrPath;
	
	/**
	 * Check if a path exists. Will not check deleted or archived.
	 * @param path - The path of the asset to check
	 */
	public ExistsRequest(String path) {
		this.assetIdOrPath = path;
	}
	
	/**
	 * Check if an asset exists by id. Will return true even if asset is deleted.
	 * @param id - The id of the asset
	 */
	public ExistsRequest(Integer id) {
		this.assetIdOrPath = id.toString();
	}
	
}
