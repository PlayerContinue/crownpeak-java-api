package org.crownpeak.api.request;

public class AssetRouteRequest extends APIRequest {
	/**
	 * Id of asset to route
	 */
	public int assetId;
	/**
	 * Id of the state to route to
	 */
	public int stateId;
	
	/**
	 * 
	 * @param assetId -  Id of asset to route
	 * @param stateId - Id of the state to route to
	 */
	public AssetRouteRequest(int assetId, int stateId) {
		this.assetId = assetId;
		this.stateId = stateId;
	}
}
