package com.ServicesGroupKT.TestCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC02_Get_Each_Country extends BaseClass
{

	@Test(dataProvider="countryCode")
	/**
	 * 
	 * @param COUNTRY_ISO2CODE
	 */
	void getEachCounty(String COUNTRY_ISO2CODE) throws InterruptedException
	{
		//specify the base URL
		RestAssured.baseURI = "http://services.groupkt.com/country";

		//object created for request
		RequestSpecification httpRequest = RestAssured.given();

		//object created for response with specific country code
		Response response = httpRequest.request(Method.GET, "/get/iso2code/"+COUNTRY_ISO2CODE);

		Thread.sleep(1000);

		//validating the status code
		int statusCode = response.getStatusCode();
		s_assert.assertEquals(statusCode, 200);

		//validating the response code
		String alpha2_code = response.jsonPath().getString("alpha2_code");
		s_assert.assertEquals(alpha2_code, COUNTRY_ISO2CODE);

		//validating the response code
		String messages = response.jsonPath().getString("messages");
		s_assert.assertEquals(messages, "Country found matching code ["+COUNTRY_ISO2CODE+"].");

	}


	@DataProvider(name="countryCode")
	String[][] getCountryCode()
	{
		String countryCode [][] = {{"US"},{"DE"},{"GB"}};
		return (countryCode);
	}
}
