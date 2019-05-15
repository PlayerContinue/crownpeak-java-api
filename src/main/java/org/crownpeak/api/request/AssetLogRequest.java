package org.crownpeak.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AssetLogRequest extends APIRequest {

	/**
	 * Asset id to attach log to
	 */
	public Integer assetId;
	
	/**
	 * The message to log
	 */
	public String message;
	
	/**
	 * 
	 * @param message - The message to log
	 * @param assetId - Asset id to attach log to
	 */
	public AssetLogRequest(Integer assetId, String message) {
		this.assetId = assetId;
		this.message = message;
	}
	
	/**
	 * 
	 * @param message - The message to log
	 */
	public AssetLogRequest(String message) {
		this.assetId = null;
		this.message = message;
	}
	
}
