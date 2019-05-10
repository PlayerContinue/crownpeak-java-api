package org.crownpeak.api;
/**
 * The class created to authenticate connections to the cms
 * @author dgree
 *
 */
public class AuthenticateRequest extends APIRequest {

	public String username;
	public String password;
	
	public AuthenticateRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
	


}
