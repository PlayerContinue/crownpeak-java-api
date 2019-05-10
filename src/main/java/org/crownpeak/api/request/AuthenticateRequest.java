package org.crownpeak.api.request;

/**
 * The class created to authenticate connections to the cms
 * @author dgree
 *
 */
public class AuthenticateRequest extends APIRequest {

	public String username;
	public String password;
	public String instance;
	public boolean remember_me;
	public int timeZoneOffsetMinutes;
	
	public AuthenticateRequest(String username, String password, String instance) {
		this.username = username;
		this.password = password;
		this.instance = instance;
		this.remember_me = false;
		this.timeZoneOffsetMinutes = -480;
	}
	


}
