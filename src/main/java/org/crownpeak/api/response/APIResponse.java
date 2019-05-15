package org.crownpeak.api.response;

import org.crownpeak.api.Util;

public class APIResponse {

	public String resultCode = null;
	public String errorMessage;
	public int internalCode;
	public String Message;
	public String ExceptionMessage;
	public String ExceptionType;
	public int statusCode;
	
	/**
	 * Checks if the requested call was successful
	 * @return True on success, false on failure
	 */
	public boolean isSuccessful() {
		if(resultCode!=null && resultCode.contentEquals(Util.ResultCode.SUCCESS.value())) {
			return true;
		}else {
			return false;
		}
	}
	
}
