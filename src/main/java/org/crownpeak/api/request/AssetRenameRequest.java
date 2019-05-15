package org.crownpeak.api.request;

public class AssetRenameRequest extends APIRequest {
	/**
	 * Asset id of the asset to rename
	 */
	public int assetId;
	/**
	 * The desired name of the the asset
	 */
	public String newName;
	
	/**
	 * Create a new instance of the AssetRenameRequest
	 * @param assetId - Asset id of the asset to rename
	 * @param newName - The desired name of the the asset
	 */
	public AssetRenameRequest(int assetId, String newName) {
		this.assetId = assetId;
		this.newName = newName;
	}
}
