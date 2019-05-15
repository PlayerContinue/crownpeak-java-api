package org.crownpeak.api.response;

public class AssetPublishRefreshResponse extends APIResponse {
	/**
	 * List of all publishing sessions
	 */
	public int[] publishingSessionIds;
	/**
	 * The session id being used
	 */
	public int publishingSessionId;
}
