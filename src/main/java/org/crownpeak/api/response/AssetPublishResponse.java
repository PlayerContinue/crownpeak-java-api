package org.crownpeak.api.response;

public class AssetPublishResponse extends APIResponse {
	/**
	 * Actions required after publish
	 */
	public String requiredAction;
	/**
	 * The session in which the publishing is occurring.
	 */
	public int publishingSessionId;
}
