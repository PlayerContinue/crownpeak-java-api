package org.crownpeak.api.response;

public class WorkflowStepCommandsData {
	public int stepId;
	public int stateId;
	public String subject;
	public String description;
	public int workflowId;
	public WorkflowCommandData[] commands;
	public String modifiedBy;
	public String modifiedDate;
	public int usage;
}
