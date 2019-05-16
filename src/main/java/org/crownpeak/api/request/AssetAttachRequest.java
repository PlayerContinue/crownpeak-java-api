package org.crownpeak.api.request;

public class AssetAttachRequest extends APIRequest {
	/**
	 * The asset Id of the asset to attach to
	 */
	public int assetId;
	/**
	 * The bytes of the file. Will be converted into a base64 string
	 */
	public byte[] bytes;
	/**
	 * The desired name of the file on attaching
	 */
	public String originalFileName;
	
	/**
	 * Create an AssetAttachRequest
	 * @param assetId - The asset Id of the asset to attach to
	 * @param bytes - The bytes of the file. Will be converted into a base64 string
	 * @param originalFileName - The desired name of the file on attaching
	 */
	public AssetAttachRequest(int assetId, byte[] bytes, String originalFileName) {
		this.assetId = assetId;
		this.bytes = bytes;
		this.originalFileName = originalFileName;
	}
}
