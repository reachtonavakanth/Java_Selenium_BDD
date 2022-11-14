package com.poc.api;

import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BuildRequest {

	
	/*
	 * @author:Navakanth
	 * Description: To create a Java Object to String or JSON object 
	 */
	
	public String buildjson(HashMap<String, String> map) throws JsonProcessingException {

		ObjectMapper obj = new ObjectMapper();
		return obj.writeValueAsString(map);

	}

}
