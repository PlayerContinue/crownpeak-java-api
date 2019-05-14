package org.crownpeak.api.request;
import org.crownpeak.api.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AssetCreateRequest extends APIRequest {
	
	/**
	 * The desired name of the new asset
	 */
	public String newName = null;
	/**
	 * The id of the destination folder
	 */
	public Integer destinationFolderId = null;
	/**
	 * The id of the model desired to be used
	 */
	public Integer modelId = 0;
	/**
	 * The type of asset to be created
	    NONE (0),
		FILE (2),
		FOLDER (4),
		MOUNT(9),
		CONNECTOR(10);
	 */
	public Integer type = Util.AssetTypes.FILE.value();
	/**
	 * The desired subtype of the asset
	 */
	public Integer subtype = 0;
	/**
	 * The developer template language if created a developer file, is otherwise ignored
	 * 1 is C#
	 */
	public Integer devTemplateLanguage = 0;
	/**
	 * The desired of the templateId
	 */
	public Integer templateId = 0;
	/**
	 * The id of the workflow
	 */
	public Integer workflowId = 0;
	/**
	 * Run the new asset
	 */
	public boolean runNew = false;
	/**
	 * createChildren
	 */
	public boolean createChildren = false;
	
	
	
	
	/**
	 * Empty Create Request
	 */
	public AssetCreateRequest() {
		
	}
	
	/**
	 * Create a new request to create an asset from a model
	 * @param newName - The name of the new asset
	 * @param destinationFolderId - The folder the asset will be stored in
	 * @param modelId - The modelId to use to create the new asset
	 */
	public AssetCreateRequest(String newName, int destinationFolderId, int modelId) {
		this.newName = newName;
		this.destinationFolderId = destinationFolderId;
		this.modelId = modelId;
		this.type = Util.AssetTypes.FILE.value();
	}
	
	
	
	/**
	 * Create a C# developer Asset
	 * @param newName - The name of the new asset
	 * @param destinationId - The folder to create the asset in
	 */
	public void createDevAsset(String newName, int destinationFolderId) {
		this.newName = newName;
		this.destinationFolderId = destinationFolderId;
		this.devTemplateLanguage = 1;//C# developer template
		this.templateId = 0;
		this.modelId = 0;
		this.type = Util.AssetTypes.FILE.value();
		this.subtype = 1;
	}
	
	
}
