package com.ServicesGroupKT.TestCases;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import com.ServicesGroupKT.Utilities.ReadConfig;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass
{
	SoftAssert s_assert = new SoftAssert();
	ReadConfig readConfig = new ReadConfig();

	//object created for request
	public static RequestSpecification httpRequest;

	//object created for response
	public static Response response;

	public Logger logger;

	@BeforeClass
	public void setup()
	{
		logger = Logger.getLogger("ServicesGroupKTRestAPI");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
	}
}
