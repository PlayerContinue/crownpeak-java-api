package org.crownpeak.api.request;

public class AssetMoveRequest extends APIRequest {

	/**
	 * The id of the asset to move
	 */
	public int assetId;
	/**
	 * The folder id to move the asset to
	 */
	public int destinationFolderId;
	
	/**
	 * 
	 * @param assetId - The id of the asset to move
	 * @param destinationFolderId - The folder id to move the asset to
	 */
	public AssetMoveRequest(int assetId, int destinationFolderId) {
		this.assetId = assetId;
		this.destinationFolderId = destinationFolderId;
	}
	
}
