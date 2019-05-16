package org.crownpeak.api.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Interface containing functions used in all APIRequests
 * @author dgree
 *
 */
public class APIRequest{
	/**
	 * Function for converting APIRequest to java
	 * @return - The APIRequest as json
	 * @throws JsonProcessingException - JSON was not formed correctly.
	 */
	public String toJSON() throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		return mapper.writeValueAsString(this);
	}
}