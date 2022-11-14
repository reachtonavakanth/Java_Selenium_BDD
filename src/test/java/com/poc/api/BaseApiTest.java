package com.poc.api;

import java.io.File;

import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.poc.utils.Utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BaseApiTest {

	public String dataFile = "resources" + File.separator + "TestData.xlsx";
	public String[] sheet = { "testdata", "config", "api", "apiconfig" };

	/*
	 * @author:Navakanth Description: To perform a get request
	 */

	public Response verifyGet(String url) {
		return RestAssured.given().when().get(url);
	}

	/*
	 * @author:Navakanth Description: To verify a Post request
	 */

	public Response verifyPost(String str, String url) throws JsonProcessingException {

		return RestAssured.given().body(str).when().post(url);

	}

	/*
	 * @author:Navakanth Description: To validate Status codes
	 */

	public void validateStatusCode(int expected, Response rsp) {

		Assert.assertTrue(expected == rsp.getStatusCode());
	}

	/*
	 * @author:Navakanth Description: To get value from Json response
	 */

	public String getValue(Response rsp, String key) {
		JsonPath jsonPath = new JsonPath(rsp.asString());

		return jsonPath.getString(key);
	}

	/*
	 * @author:Navakanth Description: To get JsonPath object
	 */
	public JsonPath getJsonPathObj(Response rsp) {
		return new JsonPath(rsp.asString());
	}

}
