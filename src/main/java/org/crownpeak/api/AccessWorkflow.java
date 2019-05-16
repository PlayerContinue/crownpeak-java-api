package org.crownpeak.api;

import org.crownpeak.api.request.APIRequest;
import org.crownpeak.api.response.WorkflowReadResponse;
import org.crownpeak.api.response.WorkflowsReadResponse;

public class AccessWorkflow {

	public API api;
	
	/**
	 * Create an new instance of the object
	 * @param api - An authenticated api
	 * @throws Exception - On the API not being authenticated
	 */
	public AccessWorkflow(API api) throws Exception {
		if(!api.isAuthenticated()) {
			throw new Exception("API is not authenticated. No functions can be run.");
		}
		this.api = api;
	}
	
	/**
	 * Retrieve information about a workflow by the id
	 * @param workflowId - The id of the workflow. 
	 * @return - Information about the workflow
	 */
	public WorkflowReadResponse read(int workflowId) {
		return MakeRequest.makeRequest("/Workflow/Read/" + workflowId, new APIRequest(), api,WorkflowReadResponse.class);
	}
	
	/**
	 * Retrieve a list of all workflows in the cms, does not return workflows in projects
	 * @return - List of all workflows not in projects
	 */
	public WorkflowsReadResponse read() {
		return MakeRequest.makeRequest("/Workflow/Read/", new APIRequest(), api,WorkflowsReadResponse.class);
	}
	
}
