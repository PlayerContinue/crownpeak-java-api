package org.crownpeak.api.request;

public class AssetAttachRequest extends APIRequest {
	public int assetId;
	public byte[] bytes;
	public String originalFileName;
	
	public AssetAttachRequest(int assetId, byte[] bytes, String originalFileName) {
		this.assetId = assetId;
		this.bytes = bytes;
		this.originalFileName = originalFileName;
	}
}
