package org.crownpeak.api.response;

import org.crownpeak.api.Util;

public class APIResponse {

	/**
	 * The result of the call
	 */
	public String resultCode = null;
	/**
	 * Error message. Blank if none occurred
	 */
	public String errorMessage;
	/**
	 * Internal code
	 */
	public int internalCode;
	/**
	 * Any messages from the server
	 */
	public String Message;
	/**
	 * Exceptions that occurred on the server
	 */
	public String ExceptionMessage;
	/**
	 * The type of exception that occurred
	 */
	public String ExceptionType;
	/**
	 * The statusCode
	 */
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
