package org.crownpeak.api.response;

import java.util.ArrayList;

public class WorkflowDataResponse {
	public int assetId;
	public String description;
	public int id;
	public String modifiedBy;
	public String modifiedDate;
	public String name;
	public ArrayList<WorkflowStepCommandsData> stepCommands;
	public int usage;
	
}
