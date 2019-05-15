package org.crownpeak.api.request;

public class AssetPublishRequest extends APIRequest {
	/**
	 * List of asset ids that should be published
	 */
	public int[] assetIds;
	/**
	 * True if dependencies should be skipped, false otherwise
	 */
	public boolean skipDependencies;
	
	/**
	 * Create a Publishing request
	 * @param assetIds - List of asset ids that should be published
	 * @param skipDependencies - True if dependencies should be skipped, false otherwise
	 */
	public AssetPublishRequest(int[] assetIds, boolean skipDependencies) {
		this.assetIds = assetIds;
		this.skipDependencies = skipDependencies;
	}
}
