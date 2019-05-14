package org.crownpeak.api.response;

public class AssetExistsResponse extends APIResponse {
	/**
	 * True if the asset exists, false if does not. Null if there is an error
	 */
	public Boolean exists = null;
	/**
	 * The asset id of the asset if it exists
	 */
	public int assetId = -1;
}
