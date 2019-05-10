package org.crownpeak.api.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Interface containing functions used in all APIRequests
 * @author dgree
 *
 */
public class APIRequest{
	
	public String toJSON() throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}
}