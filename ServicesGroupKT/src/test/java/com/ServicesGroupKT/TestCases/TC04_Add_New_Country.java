package com.ServicesGroupKT.TestCases;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC04_Add_New_Country extends BaseClass{

	@SuppressWarnings("unchecked")
	@Test
	void addNewCountry() throws InterruptedException
	{
		//specify the base URL
		RestAssured.baseURI = "http://services.groupkt.com/country";

		//object created for request
		RequestSpecification httpRequest = RestAssured.given();

		//Request payload for adding new country
		JSONObject reqParams = new JSONObject();

		reqParams.put("name", "Test Country");
		reqParams.put("alpha2_code", "TC");
		reqParams.put("alpha3_code", "TCY");

		//adding header to the request
		httpRequest.header("content-type","application/json");

		//adding up the params and converting the request to json format
		httpRequest.body(reqParams.toJSONString());

		//object created for response
		Response response = httpRequest.request(Method.POST, "/add");

		Thread.sleep(1000);

		//validating the status code
		int statusCode = response.getStatusCode();
		s_assert.assertEquals(statusCode, 200);

		//validating the response code
		String responseCode = response.jsonPath().getString("responseCode");
		s_assert.assertEquals(responseCode, "Success");

	}

}
