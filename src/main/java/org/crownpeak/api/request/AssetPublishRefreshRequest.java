package org.crownpeak.api.request;

public class AssetPublishRefreshRequest extends APIRequest {
	/**
	 * List of folder ids to republish
	 */
	public int[] assetIds;
	/**
	 * The id of the publishing package to republish
	 */
	public int publishingServerId;
	/**
	 * Determines whether to include dependencies when refreshing an asset
	 */
	public boolean skipDependencies;
	
	/**
	 * Creates a new instance of AssetPublishRefreshRequest
	 * @param assetIds - List of folder ids to republish
	 * @param publishingServerId - The id of the publishing package to republish
	 * @param skipDependencies - Determines whether to include dependencies when refreshing an asset
	 */
	public AssetPublishRefreshRequest(int[] assetIds, int publishingServerId, boolean skipDependencies) {
		this.assetIds = assetIds;
		this.publishingServerId = publishingServerId;
		this.skipDependencies = skipDependencies;
	}
}
