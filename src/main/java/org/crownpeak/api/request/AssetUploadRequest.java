package org.crownpeak.api.request;

public class AssetUploadRequest extends APIRequest {
	/**
	 * Desired binary file to upload as a byte array
	 */
	public byte[] bytes;
	
	/**
	 * The folder to upload the binary file to
	 */
	public int destinationFolderId;
	/**
	 * The id of the model to be used, can be left blank
	 */
	public int modelId = -1;
	/**
	 * Desired name of the uploaded asset
	 */
	public String newName;
	/**
	 * Desired workflow Id, will be overridden by model and can be left blank
	 */
	public int workflowId = -1;
	
	/**
	 * 
	 * @param bytes - Desired binary file to upload as a byte array
	 * @param destinationFolderId - The folder to upload the binary file to
	 * @param newName - Desired name of the uploaded asset
	 */
	public AssetUploadRequest(byte[] bytes,int destinationFolderId, String newName) {
		this.bytes = bytes; 
		this.newName = newName;
		this.destinationFolderId = destinationFolderId;
	}
	
	/**
	 * 
	 * @param bytes - Desired binary file to upload as a byte array
	 * @param destinationFolderId - The folder to upload the binary file to
	 * @param newName - Desired name of the uploaded asset
	 * @param modelId - The id of the model to be used
	 */
	public AssetUploadRequest(byte[] bytes, int destinationFolderId, String newName, int modelId) {
		this(bytes, destinationFolderId,newName);
		this.modelId = modelId;
	}
	
	/**
	 * 
	 * @param bytes - Desired binary file to upload as a byte array
	 * @param destinationFolderId - The folder to upload the binary file to
	 * @param workflowId - Desired workflow Id, will be overridden by model and can be left blank
	 * @param newName - Desired name of the uploaded asset
	 */
	public AssetUploadRequest(byte[] bytes, int destinationFolderId, int workflowId, String newName) {
		this(bytes, destinationFolderId,newName);
		this.workflowId = workflowId;
	}
	
	
	
	
}
