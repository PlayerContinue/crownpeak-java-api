package org.crownpeak.api.request;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Request to update an asset
 * @author dgree
 *
 */
public class AssetUpdateRequest extends APIRequest {
	/**
	 * The assetId of the asset to update
	 */
	public int assetId;
	/**
	 * Fields to add to an asset
	 */
	public HashMap<String,String> fields;
	/**
	 * Fields to remove from the asset
	 */
	public ArrayList<String> fieldsToDelete;
	
	/**
	 * Create a request for update an Asset
	 * @param assetId - The asset Id to update
	 * @param fields - A dictionary containing key pair list of values to update. If the key does not exists a new field will be created. Set null to skip.
	 */
	public AssetUpdateRequest(int assetId, HashMap<String, String> fields) {
		this.assetId = assetId;
		this.fields = fields;
		this.fieldsToDelete = null;
	}
	
	/**
	 * Create a request for update an Asset
	 * @param assetId - The asset Id to update
	 * @param fields - A dictionary containing key pair list of values to update. If the key does not exists a new field will be created. Set null to skip.
	 * @param fieldsToDelete - A list of keys to be deleted from the asset. Set null to skip.
	 */
	public AssetUpdateRequest(int assetId,HashMap<String,String> fields, ArrayList<String> fieldsToDelete) {
		this.fieldsToDelete = fieldsToDelete;
		this.fields = fields;
		this.assetId = assetId;
	}
	
	
}
