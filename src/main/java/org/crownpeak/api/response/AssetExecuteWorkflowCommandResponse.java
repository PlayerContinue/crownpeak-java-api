package org.crownpeak.api.response;

public class AssetExecuteWorkflowCommandResponse extends APIResponse {
	/**
	 * Required Actions
	 */
	public String requiredAction;
	/**
	 * The session id of the publishing asset. If less than or equal 0 it has failed to publish.
	 */
	public int publishingSessionId;
	/**
	 * The asset that was moved through workflow
	 */
	public AssetResponse asset;
}
