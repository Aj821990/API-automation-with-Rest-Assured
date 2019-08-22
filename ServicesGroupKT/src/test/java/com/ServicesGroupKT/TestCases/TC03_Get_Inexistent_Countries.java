package com.ServicesGroupKT.TestCases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ServicesGroupKT.Utilities.XLUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC03_Get_Inexistent_Countries extends BaseClass
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

		//validating the status code
		int statusCode = response.getStatusCode();
		s_assert.assertEquals(statusCode, 404);

		//validating the response code
		String messages = response.jsonPath().getString("messages");
		s_assert.assertEquals(messages, "No matching country found for requested code ["+COUNTRY_ISO2CODE+"].");

		Thread.sleep(1000);

	}


	@DataProvider(name="countryCode")
	String[][] getCountryCode() throws IOException
	{
		//declare path for the excel file
		String path = System.getProperty("user.dir")+"/src/test/java/TestData/COUNTRY_ISO2CODE.xlsx";

		//count row number
		int rownum = XLUtils.getRowCount(path, "Sheet1");

		//count col number
		int colnum = XLUtils.getCellCount(path, "Sheet1", 1);

		//declaring an array of type string
		String countryCode[][] = new String[rownum][colnum];

		//adding data from excel file to the string array object 
		for(int i=1; i<=rownum;i++)
		{
			for(int j=0;j<colnum;j++)
			{
				countryCode[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}

		return (countryCode);
	}
}
