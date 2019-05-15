package org.crownpeak.api;

import org.crownpeak.api.request.APIRequest;
import org.crownpeak.api.response.WorkflowReadResponse;
import org.crownpeak.api.response.WorkflowsReadResponse;

public class AccessWorkflow {

	public API api;
	
	/**
	 * Create an new instance of the object
	 * @param api - An authenticated api
	 * @throws Exception 
	 */
	public AccessWorkflow(API api) throws Exception {
		if(!api.isAuthenticated()) {
			throw new Exception("API is not authenticated. No functions can be run.");
		}
		this.api = api;
	}
	
	public WorkflowReadResponse read(int id) {
		return MakeRequest.makeRequest("/Workflow/Read/" + id, new APIRequest(), api,WorkflowReadResponse.class);
	}
	
	public WorkflowsReadResponse read() {
		return MakeRequest.makeRequest("/Workflow/Read/", new APIRequest(), api,WorkflowsReadResponse.class);
	}
	
}
