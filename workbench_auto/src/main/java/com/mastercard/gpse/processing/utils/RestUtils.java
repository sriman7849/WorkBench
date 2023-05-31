package com.mastercard.gpse.processing.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mastercard.gpse.processing.configuration.RestConfiguration;
import com.mastercard.gpse.processing.domain.RequestResponseParams;
import freemarker.core.Environment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import static org.junit.Assert.assertEquals;

import io.restassured.response.Response;


public class RestUtils {

	protected Environment env;

	public TestContext mtafTestContext;


	//ConfigLoader config;


	RestConfiguration restConfiguration;


	JsonUtils jsonUtil;



	RequestResponseParams params;


	//Utility utility;

	Logger logger = (Logger) LogManager.getLogger(RestUtils.class);
	List<HashMap<String, String>> terminalDetailsFromDB = new ArrayList<>();
	String terminalCountBasedOnLimit = null;

	// variables for txn searches api
	String segment = null;
	String transactionMessagingSystem = null;
	String valueAddedService = null;
	String terminalId = null;
	String networkCode = null;
	String transactionCount = null;
	String settlementStatus = null;
	String transactionType = null;
	String reqParams = null;
	public static boolean isTxnsPresent = false;
	List<HashMap<String, String>> txnDetailsDBMapForTxnReq = new ArrayList<>();

	/*public Response postRequest(String requestBody, String resourceUri) {
		//String bankCode = getBankCode();
		RestAssured.baseURI = restConfiguration.getBaseURI();
		RestAssured.config = restConfiguration.getRestAssuredConfig();
		//logger.info("Post Request :" + restConfiguration.getBaseURI()+ resourceUri);
		Response response = RestAssured.given().when()
				.contentType(config.getContenttype())
				.headers(ApiConstants.HEADER_BANK_CODE, bankCode)
				.body(requestBody).post(resourceUri);
		return response;

	}*/


	public static void validateResponseCode(Response response, String input) {
		//logger.info(response.asString());
		response.then().statusCode(Integer.valueOf(input));

	}

}
