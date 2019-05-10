package org.crownpeak.api.request;

public class ExistsRequest extends APIRequest {

	public String assetIdOrPath;
	
	public ExistsRequest(String path) {
		this.assetIdOrPath = path;
	}
	
	public ExistsRequest(Integer id) {
		this.assetIdOrPath = id.toString();
	}
	
}
