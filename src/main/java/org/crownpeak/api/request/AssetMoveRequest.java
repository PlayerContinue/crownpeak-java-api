package org.crownpeak.api.request;

public class AssetMoveRequest extends APIRequest {

	public int assetId;
	public int destinationFolderId;
	
	public AssetMoveRequest(int assetId, int destinationFolderId) {
		this.assetId = assetId;
		this.destinationFolderId = destinationFolderId;
	}
	
}
