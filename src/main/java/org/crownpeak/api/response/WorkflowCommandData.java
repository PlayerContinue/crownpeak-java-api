package org.crownpeak.api.response;

public class WorkflowCommandData {
	public int id;
	public int workflowId;
	public int workflowStep;
	public int destinationStatus;
	public int commandId;
	public String command;
	public int filterId;
	public boolean hasVerifyCommand;
	public boolean hasRequestComment;
	public boolean isPublishingStep;
}
