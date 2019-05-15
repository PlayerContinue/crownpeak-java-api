package org.crownpeak.api.request;

public class AssetExecuteWorkflowCommandRequest extends APIRequest {
	/**
	 * The asset Id to move through workflow
	 */
	public int assetId;
	/**
	 * The id of the command step to send the workflow through. This can be found by using the AccessWorkflow.Read command
	 */
	public int commandId;
	/**
	 * SkipDependencies if true, publish them otherwise.
	 */
	public boolean skipDependencies;
	
	/**
	 * Create the ExecuteWorkflowCommandRequest
	 * @param assetId - The asset Id to move through workflow
	 * @param commandId - The id of the command step to send the workflow through. This can be found by using the AccessWorkflow.Read command
	 * @param skipDependencies - SkipDependencies if true, publish them otherwise.
	 */
	public AssetExecuteWorkflowCommandRequest(int assetId, int commandId, boolean skipDependencies) {
		this.assetId = assetId;
		this.commandId = commandId;
		this.skipDependencies = skipDependencies;
	}
}
