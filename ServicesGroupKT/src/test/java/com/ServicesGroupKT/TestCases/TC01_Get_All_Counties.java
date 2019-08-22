package com.ServicesGroupKT.TestCases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;

/**
 * Unit test for simple App.
 */
public class TC01_Get_All_Counties extends BaseClass
{

	@BeforeTest
	void getAllCounties() throws InterruptedException
	{

		//specify the base URL
		RestAssured.baseURI = readConfig.getApplicationURL();

		//object created for request
		httpRequest = RestAssured.given();

		//object created for response
		response = httpRequest.request(Method.GET, "/get/all");

		Thread.sleep(1000);
	}

	@Test
	void checkStatusCode()
	{
		//validating the status code
		int statusCode = response.getStatusCode();
		s_assert.assertEquals(statusCode, 200);
	}

	@Test
	void verifySpecficCountries()
	{
		// suppose the list key is "countries" in the json response
		// and we are storing the values in an array list
		List <HashMap<String,Object>> countryList = response.jsonPath().getList("countries");

		// declaring the list to save the country codes
		List<String> alpha2_code_list = new ArrayList<String>();

		//for loop 
		for(int i=0; i<countryList.size(); i++)
		{
			//hashmap for saving the json objects
			HashMap<String, Object> countryDetails = countryList.get(i);

			//getting the country code from json and saving it in string
			String alpha2_code = (String) countryDetails.get("alpha2_code");
			logger.info("country code is :" + alpha2_code);

			//adding the country codes in normal list and not json list
			alpha2_code_list.add(alpha2_code);
		}


		// validating if country code US is present in the list
		if(alpha2_code_list.contains("US"))
		{
			s_assert.assertTrue(true);
		}
		else
		{
			s_assert.assertTrue(false);
		}

		// validating if country code DE is present in the list
		if(alpha2_code_list.contains("DE"))
		{
			s_assert.assertTrue(true);
		}
		else
		{
			s_assert.assertTrue(false);
		}

		// validating if country code GB is present in the list
		if(alpha2_code_list.contains("GB"))
		{
			s_assert.assertTrue(true);
		}
		else
		{
			s_assert.assertTrue(false);
		}
	}
}
