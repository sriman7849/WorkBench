package com.mastercard.gpse.processing.configuration;

import freemarker.core.Environment;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class RestConfiguration {
	
	static final String STAGE = "stage";
	//static String KEYSTOREFILEPATH = "src\\main\\resources\\config\\stage\\stage.sa.api.mi.mastercard.com.pfx";
	//static String CKTPASSPHARSE="Desktopcert@3";
	Logger logger = (Logger) LogManager.getLogger(ApiConfig.class);
	
	protected Environment env;
	
	public RestAssuredConfig getRestAssuredConfig() {
		

		return RestAssured.config;
	}

	
	/*public String getBaseURI()
	{
		return env.getProperty("acquirer.api.base.uri");
	}*/

}
