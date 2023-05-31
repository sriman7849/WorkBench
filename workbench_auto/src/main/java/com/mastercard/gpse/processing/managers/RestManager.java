/*
package com.mastercard.processing.eu.managers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.mastercard.pts.integrated.acquiring.constants.ConstantUtils;
import com.mastercard.pts.integrated.acquiring.constants.RestConstants;
import com.mastercard.pts.integrated.acquiring.domain.rest.*;
import com.mastercard.pts.integrated.acquiring.domain.rest.merchant.Merchant;
import com.mastercard.pts.integrated.acquiring.domain.rest.outlet.Outlet;
import com.mastercard.pts.integrated.acquiring.domain.rest.outlet.Outlets;
import com.mastercard.pts.integrated.acquiring.domain.rest.outlet.Profile;
import com.mastercard.pts.integrated.acquiring.domain.rest.qrterminal.DeliveryMailingAddress;
import com.mastercard.pts.integrated.acquiring.domain.rest.qrterminal.QRCodesToPrint;
import com.mastercard.pts.integrated.acquiring.domain.rest.terminal.ExtendedConfig;
import com.mastercard.pts.integrated.acquiring.domain.rest.terminal.Terminal;
import com.mastercard.pts.integrated.acquiring.domain.rest.terminal.Terminals;
import com.mastercard.pts.integrated.acquiring.steps.rest.OutletServiceSteps;
import com.mastercard.pts.integrated.acquiring.steps.rest.TerminalServiceSteps;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;
import net.minidev.json.parser.ParseException;
import org.apache.log4j.Logger;
import org.jbehave.core.model.ExamplesTable;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class RestManager {


	final Logger logger = Logger.getLogger(RestUtils.class);
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

	public Response postRequest(String requestBody, String resourceUri) {
		String bankCode = getBankCode();
		RestAssured.baseURI = restConfiguration.getBaseURI();
		RestAssured.config = restConfiguration.getRestAssuredConfig();
		logger.info("Post Request :" + restConfiguration.getBaseURI()
				+ resourceUri);
		Response response = RestAssured.given().when()
				.contentType(config.getContenttype())
				.headers(RestConstants.HEADER_BANK_CODE, bankCode)
				.body(requestBody).post(resourceUri);
		return response;

	}

	public Response putRequest(String requestBody, String resourceUri) {
		String bankCode = getBankCode();
		RestAssured.baseURI = restConfiguration.getBaseURI();
		RestAssured.config = restConfiguration.getRestAssuredConfig();
		logger.info("Put Request :" + restConfiguration.getBaseURI()
				+ resourceUri);
		logger.info("Request body: " + requestBody);
		Response response = RestAssured.given().when()
				.contentType(config.getContenttype())
				.headers(RestConstants.HEADER_BANK_CODE, bankCode)
				.body(requestBody).put(resourceUri);
		return response;
	}

	public Response postRequest(Map<String, String> payload, String resourceUri) {
		RestAssured.baseURI = restConfiguration.getBaseURI();
		RestAssured.config = restConfiguration.getRestAssuredConfig();
		logger.info("Post Request :" + restConfiguration.getBaseURI()
				+ resourceUri);
		Response response = RestAssured.given()
				.contentType(config.getContenttype())
				.headers(RestConstants.HEADER_BANK_CODE, config.getBankcode())
				.body(payload).when().post(resourceUri);

		return response;
	}

	public Response getRequest(String resourceUri,
			Map<String, String> queryParameters) {
		RestAssured.baseURI = restConfiguration.getBaseURI();
		RestAssured.config = restConfiguration.getRestAssuredConfig();
		logger.info("Get Request :" + restConfiguration.getBaseURI()
				+ resourceUri);
		Response response = RestAssured
				.given()
				.when()
				.contentType(config.getContenttype())
				.headers(RestConstants.HEADER_CORRELATION_ID,
						config.getCorrelationid(),
						RestConstants.HEADER_BANK_CODE, config.getBankcode())
				.queryParams(queryParameters).get(resourceUri);
		return response;

	}

	public String getResponseString(Response response) {
		String responseString = response.then().assertThat().extract()
				.asString();
		return responseString;
	}

	public Response getRequest(String resourceUri) {
		String bankCode = getBankCode();
		RestAssured.baseURI = restConfiguration.getBaseURI();
		RestAssured.config = restConfiguration.getRestAssuredConfig();
		logger.info("Get Request :" + restConfiguration.getBaseURI()
				+ resourceUri);
		Response response = RestAssured
				.given()
				.when()
				.contentType(config.getContenttype())
				.headers(RestConstants.HEADER_CORRELATION_ID,
						config.getCorrelationid(),
						RestConstants.HEADER_BANK_CODE, bankCode)
				.get(resourceUri);
		return response;

	}

	public void validateResponseBody(Response response, String responsefile) {
		String expectedJson = jsonUtil.getJsonStringFromFile(responsefile);
		String responseString = response.then().assertThat().extract()
				.asString();
		logger.info("Expected Response:" + responseString);
		Assert.assertEquals(expectedJson, responseString);

	}

	public void validateResponseBodywithSkipFields(Response response,
			String responsefile, String fields) {
		JsonObject expectedJson = jsonUtil.getJsonObjectFromFile(responsefile);
		expectedJson.remove(fields);
		String responseString = response.then().assertThat().extract()
				.asString();
		JsonParser parser = new JsonParser();
		JsonObject json = (JsonObject) parser.parse(responseString);
		json.remove(fields);
		logger.info("Expected Response:" + expectedJson.toString());
		;
		logger.info("Actual Response:" + json.toString());
		Assert.assertEquals(expectedJson.toString(), json.toString());

	}

	public void validateResponseBodywithGivenFields(Response response,
			String field) {
		try {
			// JsonObject expectedJson =
			// jsonUtil.getJsonObjectFromFile(responsefile);

			// System.out.println("Expected Response1:"+expectedJson.toString());
			*/
/*
			 * ResponseObject responseO = response.then()
			 * .extract().as(ResponseObject.class);
			 * assertEquals(responseO.getResponseCode(),)
			 *//*

			// SoftAssert softAssert = new SoftAssert();
			String responsemsg = extractValue(response.asString(), field);
			// logger.info(responseob.getResponseCode());

			// response.then().assertThat().body("responseObject.responseCode",equalTo(responseob.getResponseCode()));
			// assertEquals(responsemsg, responseob.getResponseMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void validateResponseBodyMessage(Response response,
			ResponseObject responseob) {
		try {

			String responsemsg = extractValue(response.asString(), "message");
			logger.info(responseob.getResponseMessage());
			logger.info(responsemsg);
			assertEquals(responsemsg, responseob.getResponseMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String extractValue(String response, String param) {
		DocumentContext docCtx;
		String value = "";
		try {
			// System.out.println(response);
			docCtx = JsonPath.parse(response);
			JsonPath jsonPath = JsonPath.compile("*." + param);
			value = docCtx.read(jsonPath).toString();
			value = value.replace("[", "").replace("]", "").replace("\"", "");
		} catch (Exception e) {
			value = e.getMessage();
			// e.printStackTrace();
		}
		return value;

	}

	public String extractFieldValue(Response response, String field) {
		return response.jsonPath().getString(field);
	}

	public void validateResponseCode(Response response, String input) {
		logger.info(response.asString());
		response.then().statusCode(Integer.valueOf(input));

	}

	public void validateResponseSchema(Response response, String schema) {
		logger.info(response.asString());
		logger.info(schema);
		response.then().assertThat()
				.body(matchesJsonSchemaInClasspath("restschema\\" + schema));

	}

	public void validateResponseForGivenFields(Response response, String field) {
		logger.info(response.asString());
		GetResponse merResponse = response.as(GetResponse.class);
		Merchant merchant = (Merchant) jsonUtil.getValueOf(merResponse, field);
		logger.info("ExtractedResponse:" + merchant.toString());
		// response.then().statusCode(Integer.valueOf(input));
	}

	public Object getExtractedObjectResponse(Response response, String field) {
		logger.info(response.asString());
		GetResponse merResponse = response.as(GetResponse.class);
		return jsonUtil.getValueOf(merResponse, field);
	}

	public String getExtractedResponse(Response response, String field) {
		logger.info(response.asString());
		String merResponse = response.jsonPath().getString(field);
		return merResponse;
	}

	public void validateResponse(String response, List<String> inputData) {

		*/
/*
		 * if (!inputData.isEmpty() && inputData != null) { for (String input :
		 * inputData) { //input =
		 * checkForStageScenario(reqResParams.getValidCert(),input); String
		 * inputVal = getValueFromProperties(input, response); String attribute
		 * = inputVal.split("=")[0]; String expectedData =
		 * inputVal.split("=")[1];
		 * 
		 * String actual = "null"; try { actual = extractValue(response,
		 * attribute); } catch (Exception e) {
		 * 
		 * } softAssert.andThat(expectedData.trim().toLowerCase(), actual
		 * .trim().toLowerCase(), attribute, response); }
		 *//*

	}

	public boolean validateGivenFields(
			ArrayList<HashMap<String, Object>> fieldList, Response response) {
		boolean rc = false;
		logger.info(response.asString());
		for (HashMap<String, Object> eEntry : fieldList) {
			Iterator it = eEntry.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				logger.info(pair.getKey() + " = " + pair.getValue());
				String value = pair.getValue().toString();
				String responsevalue = extractValue(response.asString(), pair
						.getKey().toString());
				if (value.equals(responsevalue)) {
					rc = true;
					logger.info("field value is correct: " + value + "==>"
							+ responsevalue);
				} else {
					logger.info("field value is not correct: " + value + "==>"
							+ responsevalue);
					return false;
				}
				it.remove(); // avoids a ConcurrentModificationException
			}
		}
		return rc;
	}

	public JSONObject updatingjson(
			ArrayList<HashMap<String, Object>> fieldList,
			JsonObject exJsonobject) {
		JSONObject merchantObject = jsonUtil.convertJsonToJSON(exJsonobject);

		for (HashMap<String, Object> eEntry : fieldList) {
			Iterator it = eEntry.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				String value;
				logger.info(pair.getKey() + " = " + pair.getValue());
				if (pair.getValue().equals("getFromContext"))
					value = mtafTestContext.get("merchantId");
				else
					value = pair.getValue().toString();
				String responsevalue = extractValue(merchantObject.toString(),
						pair.getKey().toString());
				if (!value.equals(responsevalue)) {
					merchantObject = jsonUtil.updateJson(merchantObject, pair
							.getKey().toString(), responsevalue, value);
				} else
					logger.info("already up to date");
				it.remove(); // avoids a ConcurrentModificationException
			}

		}
		return merchantObject;

	}

	*/
/**
	 * @see Function to get Correlation Id of 36 digits in UUID compliant with
	 *      RFC 4122
	 * @author e075879
	 * @return uuid
	 *//*

	public UUID getRandomCorrelationID() {
		UUID uuid = UUID.randomUUID();
		logger.info("Random UUID: " + uuid);
		return uuid;
	}

	public Response getRequestForQRTerminalExtraction(String resourceUri,
			String standard, String vendor, String qrCodeType, String offSet,
			String limit, String merchant_id, String outlet_id,
			String terminal_id) {
		Response response = null;
		RestAssured.baseURI = restConfiguration.getBaseURI();
		RestAssured.config = restConfiguration.getRestAssuredConfig();
		logger.info("Get Request :" + restConfiguration.getBaseURI()
				+ resourceUri);
		String bankCode = getBankCode();
		String correlationId = getRandomCorrelationID().toString();

		if (qrCodeType.equals("NA") && !offSet.equals("NA")
				&& !limit.equals("NA") && vendor.equals("NA")
				&& !bankCode.equals("NA") && !standard.equals("NA")
				&& !merchant_id.equals("NA") && outlet_id.equals("NA")
				&& terminal_id.equals("NA")) {
			response = RestAssured
					.given()
					.when()
					.contentType(config.getContenttype())

					.queryParams(RestConstants.QR_TERMINAL_STD, standard ,RestConstants.PARAM_OFFSET, offSet, 
							RestConstants.PARAM_LIMIT,limit, RestConstants.PARAM_MERCHANT_ID, merchant_id)
					.headers(RestConstants.HEADER_BANK_CODE,bankCode, RestConstants.HEADER_CORRELATION_ID,correlationId)
					.get(resourceUri);
		} else if (qrCodeType.equals("NA") && !offSet.equals("NA")
				&& !limit.equals("NA") && vendor.equals("NA")
				&& !bankCode.equals("NA") && !standard.equals("NA")
				&& merchant_id.equals("NA") && !outlet_id.equals("NA")
				&& terminal_id.equals("NA")) {
			response = RestAssured
					.given()
					.when()
					.contentType(config.getContenttype())
					.queryParams(RestConstants.QR_TERMINAL_STD, standard ,RestConstants.PARAM_OFFSET, offSet, 
							RestConstants.PARAM_LIMIT,limit, RestConstants.PARAM_OUTLET_ID, outlet_id)
					.headers(RestConstants.HEADER_BANK_CODE,bankCode,RestConstants.HEADER_CORRELATION_ID,correlationId)
					.get(resourceUri);
		} else if (qrCodeType.equals("NA") && !offSet.equals("NA")
				&& !limit.equals("NA") && vendor.equals("NA")
				&& !bankCode.equals("NA") && !standard.equals("NA")
				&& merchant_id.equals("NA") && outlet_id.equals("NA")
				&& !terminal_id.equals("NA")) {
			response = RestAssured
					.given()
					.when()
					.contentType(config.getContenttype())
					.queryParams(RestConstants.QR_TERMINAL_STD, standard ,RestConstants.PARAM_OFFSET, offSet, 
							RestConstants.PARAM_LIMIT,limit, RestConstants.PARAM_TERMINAL_ID, terminal_id)
					.headers(RestConstants.HEADER_BANK_CODE,bankCode,RestConstants.HEADER_CORRELATION_ID,correlationId)
					.get(resourceUri);
		} else if (qrCodeType.equals("NA") && !offSet.equals("NA")
				&& !limit.equals("NA") && vendor.equals("NA")
				&& !bankCode.equals("NA") && !standard.equals("NA")
				&& !merchant_id.equals("NA") && !outlet_id.equals("NA")
				&& !terminal_id.equals("NA")) {
			response = RestAssured
					.given()
					.when()
					.contentType(config.getContenttype())
					.queryParams(RestConstants.QR_TERMINAL_STD, standard ,RestConstants.PARAM_OFFSET, offSet, 
							RestConstants.PARAM_LIMIT,limit,RestConstants.PARAM_MERCHANT_ID, merchant_id,
							RestConstants.PARAM_OUTLET_ID, outlet_id, RestConstants.PARAM_TERMINAL_ID, terminal_id)
					.headers(RestConstants.HEADER_BANK_CODE,bankCode,RestConstants.HEADER_CORRELATION_ID,correlationId)
					.get(resourceUri);
		} else if (!vendor.equals("NA") && !standard.equals("NA")
				&& !bankCode.equals("NA") && !qrCodeType.equals("NA")
				&& !offSet.equals("NA") && !limit.equals("NA")) {
			response = RestAssured
					.given()
					.when()
					.contentType(config.getContenttype())
					.queryParams(RestConstants.PARAM_VENDOR, vendor, RestConstants.QR_TERMINAL_STD, standard,
					RestConstants.QR_CODE_TYPE, qrCodeType,RestConstants.PARAM_OFFSET, offSet, 
					RestConstants.PARAM_LIMIT,limit)
					.headers(RestConstants.HEADER_CORRELATION_ID,correlationId, RestConstants.HEADER_BANK_CODE,bankCode)
					.get(resourceUri);
		}
		else if(qrCodeType.equals("NA") && offSet.equals("NA") && limit.equals("NA") 
				&& vendor.equals("NA") && !bankCode.equals("NA") && !standard.equals("NA")){
			response = RestAssured.given().when()
					.contentType(config.getContenttype())
					.queryParams(RestConstants.QR_TERMINAL_STD, standard)
					.headers(RestConstants.HEADER_BANK_CODE,bankCode,RestConstants.HEADER_CORRELATION_ID,correlationId)
					.get(resourceUri);
		} else if (qrCodeType.equals("NA") && !offSet.equals("NA")
				&& !limit.equals("NA") && vendor.equals("NA")
				&& !bankCode.equals("NA") && !standard.equals("NA")) {
			response = RestAssured
					.given()
					.when()
					.contentType(config.getContenttype())
					.queryParams(RestConstants.QR_TERMINAL_STD, standard ,RestConstants.PARAM_OFFSET, offSet, 
							RestConstants.PARAM_LIMIT,limit)
					.headers(RestConstants.HEADER_BANK_CODE,bankCode,RestConstants.HEADER_CORRELATION_ID,correlationId)
					.get(resourceUri);
		}

		return response;

	}

	*/
/**
	 * @see Function to validate the offset and limit in the response
	 * @author e075879
	 * @return
	 * @throws ParseException
	 *//*

	public PageSummary setOffSetAndLimitInQRTerminalAPI(Response response,
			String standard, String vendor, String qrCodeType, String offSet,
			String limit, String storyName, String merchant_id,
			String outlet_id, String terminal_id) {

		String bankCode = null;
		int noOfTerminals;
		if (storyName.contains("decryption")
				|| storyName.contains("encryption")) {
			bankCode = config.getBankCodeForEncryptionDecryption();
			GetResponse getResponse = params.getResponse()
					.as(GetResponse.class);
			DecryptedResponse plainData = getResponse.getPlainData();
			List<QRCodesToPrint> qrCodesToPrint = plainData.getQrCodesToPrint();
			noOfTerminals = qrCodesToPrint.size();
		} else {
			bankCode = config.getBankcode();
			List<String> qrTerminals = response.jsonPath().getList(
					"qrCodesToPrint");
			noOfTerminals = qrTerminals.size();
		}

		terminalDetailsFromDB = dbUtil.getDetailsForGETQRTerminalServices(
				bankCode, standard, vendor, qrCodeType, merchant_id, outlet_id,
				terminal_id);
		String terminalCountFromDB = Integer.toString(terminalDetailsFromDB
				.size());

		terminalCountBasedOnLimit = limit;
		if (offSet.equals("NA"))
			offSet = "0";

		if (limit.equals("NA")) {
			limit = RestConstants.PAGESUMMARY_MAXVALUE;
			terminalCountBasedOnLimit = terminalCountFromDB;
		}

		if (Integer.parseInt(terminalCountBasedOnLimit) > Integer
				.parseInt(RestConstants.PAGESUMMARY_MAXVALUE))
			terminalCountBasedOnLimit = RestConstants.PAGESUMMARY_MAXVALUE;

		logger.info("QR Terminal Count from API: " + noOfTerminals);
		logger.info("QR Terminal Count From DB: " + terminalCountBasedOnLimit);

		if ((Integer.parseInt(terminalCountBasedOnLimit)) != noOfTerminals)
			logger.error("Count mismatch for QR terminal");

		PageSummary pageObj = new PageSummary();
		pageObj.setOffset(Integer.parseInt(offSet));
		pageObj.setLimit(Integer.parseInt(limit));
		pageObj.setCount(Integer.parseInt(terminalCountBasedOnLimit));
		pageObj.setTotal(Integer.parseInt(terminalCountFromDB));

		return pageObj;
	}

	*/
/**
	 * @see Function to get the map for dbValues for Get request of QR Terminal
	 * @author e075879
	 * @return HashMap<String, String> dbTerminalMap
	 *//*

	public GetResponse getTerminalsExtractedFromDB(Response response,
			String standard, String vendor, String qrCodeType, String offSet,
			String limit, String storyName, String merchant_id,
			String outlet_id, String terminal_id) {

		PageSummary pageObj = setOffSetAndLimitInQRTerminalAPI(response,
				standard, vendor, qrCodeType, offSet, limit, storyName,
				merchant_id, outlet_id, terminal_id);
		GetResponse qrResponseObject = new GetResponse();
		List<QRCodesToPrint> expListQRTermMap = new ArrayList<QRCodesToPrint>();

		for (int i = 0; i < Integer.parseInt(terminalCountBasedOnLimit); i++) {

			HashMap<String, String> terminalDataFromDB = terminalDetailsFromDB
					.get(i);

			String bankCode = terminalDataFromDB.get("BANK_CODE");
			String merchant = terminalDataFromDB.get("MERCHANT_NUMBER");
			String outlet = terminalDataFromDB.get("OUTLET_NUMBER");

			String outletTypeFromDB = dbUtil.getOutletTypeFromDB(outlet,
					bankCode);
			if (outletTypeFromDB.equalsIgnoreCase("P"))
				outletTypeFromDB = "POS";
			else if (outletTypeFromDB.equalsIgnoreCase("E"))
				outletTypeFromDB = "ECOM";
			else
				System.out.println("Outlet Type not in format for outlet id: "
						+ outlet);

			String extractReason = getExtractReason(
					terminalDataFromDB.get("QR_STRING_STATUS"),
					terminalDataFromDB.get("QR_STRING_REGENERATE"));
			String qrCodeStringFromDB = terminalDataFromDB
					.get("QR_STRING_DRAFT");
//			if (terminalDataFromDB.get("QR_STRING_PROD") != null)
//				qrCodeStringFromDB = terminalDataFromDB.get("QR_STRING_PROD");
//			else if (terminalDataFromDB.get("QR_STRING_EXT") != null)
//				qrCodeStringFromDB = terminalDataFromDB.get("QR_STRING_EXT");

			HashMap<String, String> MIDValues = getMIDValuesForQRTerminal(qrCodeStringFromDB);

			String qrCodeTypeFromDB = terminalDataFromDB.get("QR_CODE_TYPE");
			if (qrCodeTypeFromDB != null) {
				if (qrCodeTypeFromDB.equals("S"))
					qrCodeTypeFromDB = "STATIC";
				else if (qrCodeTypeFromDB.equals("D"))
					qrCodeTypeFromDB = "DYNAMIC";
				else
					qrCodeTypeFromDB = null;
			}

			String stdFromDB = terminalDataFromDB.get("QR_CODE_STD").equals(
					"BRT") ? "BHARAT" : terminalDataFromDB.get("QR_CODE_STD");

			String qrDelvStickerTo = terminalDataFromDB
					.get("QR_STICKER_DELIVERY_TO");
			if (qrDelvStickerTo != null) {
				if (qrDelvStickerTo.equals("I"))
					qrDelvStickerTo = "INSTITUTION";
				else if (qrDelvStickerTo.equals("O"))
					qrDelvStickerTo = "OUTLET";
				else if (qrDelvStickerTo.equals("B"))
					qrDelvStickerTo = "BRANCH";
				else if (qrDelvStickerTo.equals("M"))
					qrDelvStickerTo = "MERCHANT";
			}

			String branch = terminalDataFromDB.get("BRANCHCODE");
			// updating address details in dbMAp
			// HashMap<String, String> deliveryMailingAddress =
			// dbUtil.getDeliveryMailingDetailsForQRTerminal(
			// qrDelvStickerTo, bankCode, outlet, merchant, branch);

			HashMap<String, String> delMailAddrMap = dbUtil
					.getDelMaiAddDetailsForQRTerminal(qrDelvStickerTo,
							bankCode, outlet, merchant, branch);

			// updating values in the map
			QRCodesToPrint qrTermObject = new QRCodesToPrint();
			qrTermObject.setMerchant(merchant);
			qrTermObject.setOutlet(outlet);
			qrTermObject.setId(terminalDataFromDB.get("TERMINAL_ID"));
			qrTermObject.setOutletType(outletTypeFromDB);
			qrTermObject.setName(terminalDataFromDB.get("ACRONYM"));
			qrTermObject.setQrCodeString(qrCodeStringFromDB);
			qrTermObject.setExtractReason(extractReason);
			qrTermObject.setMastercardMid(MIDValues.get("mastercardMid"));
			qrTermObject.setVisaMid(MIDValues.get("visaMid"));
			qrTermObject.setRupayMid(MIDValues.get("rupayMid"));
			qrTermObject.setUpiMid(MIDValues.get("upiMid"));
			qrTermObject.setDiscoverMid(MIDValues.get("discoverMid"));
			qrTermObject.setAmexMid(MIDValues.get("amexMid"));
			qrTermObject.setPrivateMid(MIDValues.get("privateMid"));
			qrTermObject.setStandard(stdFromDB);
			qrTermObject.setQrCodeType(qrCodeTypeFromDB);
			qrTermObject.setLinkedTerminal(terminalDataFromDB
					.get("QR_LINKED_TERMINAL_ID"));
			DeliveryMailingAddress delAddress = new DeliveryMailingAddress();
			qrTermObject.setDeliverStickerTo(qrDelvStickerTo);
			delAddress.setBusinessName(delMailAddrMap.get("businessName"));
			delAddress.setContactName(delMailAddrMap.get("contactName"));
			Address addr = new Address();
			addr.setLine1(delMailAddrMap.get("line1"));
			addr.setLine2(delMailAddrMap.get("line2"));
			addr.setLine3(delMailAddrMap.get("line3"));
			addr.setLine4(delMailAddrMap.get("line4"));
			addr.setCity(delMailAddrMap.get("city"));
			addr.setState(delMailAddrMap.get("state"));
			addr.setCountry(delMailAddrMap.get("country"));
			addr.setZip(delMailAddrMap.get("zip"));
			delAddress.setAddress(addr);
			delAddress.setBranch(delMailAddrMap.get("branch"));
			delAddress.setPhoneNumber(delMailAddrMap.get("phoneNumber"));
			qrTermObject.setDeliveryMailingAddress(delAddress);
			expListQRTermMap.add(qrTermObject);
		}

		qrResponseObject.setPageSummary(pageObj);
		qrResponseObject.setQrCodesToPrint(expListQRTermMap);

		return qrResponseObject;
	}

	*/
/**
	 * @see Function to get the extract reason for a QR Terminal
	 * @author e075879
	 * @return reason
	 *//*

	public static String getExtractReason(String stringStatus, String regenerate) {
		String reason = null;

		if (stringStatus.equals("N"))
			reason = "NEW";
		else if (stringStatus.equals("U"))
			reason = "MODIFIED";
		else if (regenerate.equals("Y"))
			reason = "REGENERATE";

		return reason;
	}

	*/
/**
	 * @see Function to get the MID values for a QR Terminal
	 * @author e075879
	 * @return HashMap<String, String> MIDValues
	 *//*

	public HashMap<String, String> getMIDValuesForQRTerminal(String qrCodeString) {
		HashMap<String, String> MIDValues = new HashMap<String, String>();
		try {
			if (qrCodeString != null) {
				HashMap<String, String> MIDParsingMap = new HashMap<String, String>();
				int counter = 0;

				String tagId = null;
				String length = null;
				String value = null;

				while (counter < qrCodeString.length()) {
					tagId = qrCodeString.substring(counter, counter + 2);
					length = qrCodeString.substring(counter + 2, counter + 4);
					value = qrCodeString.substring(counter + 4, counter + 4
							+ Integer.parseInt(length));
					MIDParsingMap.put(tagId, value);
					counter = counter + 4 + Integer.parseInt(length);
				}

				// get values from parsed map
				if (MIDParsingMap.get("04") != null) // 04= tagId for MC
					MIDValues.put("mastercardMid", MIDParsingMap.get("04"));
				if (MIDParsingMap.get("02") != null) // 02= tagId for VISA
					MIDValues.put("visaMid", MIDParsingMap.get("02"));
				if (MIDParsingMap.get("08") != null) // 08= tagId for RUPAY
					MIDValues.put("rupayMid", MIDParsingMap.get("08"));

				// upi, discover, amex and private are not supported
				// currently=== values are null
				MIDValues.put("upiMid", null);
				MIDValues.put("discoverMid", null);
				MIDValues.put("amexMid", null);
				MIDValues.put("privateMid", null);
			}
		} catch (Exception e) {
			logger.error("Error in parsing qr Query String in method getMIDValuesForQRTerminal: "
					+ e.getMessage());
		}
		return MIDValues;
	}

	public List<String> validateResponseForGivenFieldsForTerminalBoarding(
			Response response, JSONObject expectedRequest) {
		List<String> mismatchValues = new ArrayList<>();
		try {
			logger.info(response.asString());
			GetResponse terResponse = response.as(GetResponse.class);
			Terminal terminal = terResponse.getTerminal();
			logger.info("Terminal Id from API Response:" + terminal.getId());
			// response.then().statusCode(Integer.valueOf(input));

			GetResponse expTermResponse = new ObjectMapper().readValue(
					expectedRequest.toString(), GetResponse.class);
			Terminal expectedTerminal = expTermResponse.getTerminal();

			Map<String, String> expectedMap = new HashMap<>();
			expectedMap.put("id", expectedTerminal.getId());
			expectedMap.put("status", expectedTerminal.getStatus());

			Map<String, String> actualMap = new HashMap<>();
			actualMap.put("id", terminal.getId());
			actualMap.put("status", terminal.getStatus());

			logger.info("Comparing data for terminal ID: " + terminal.getId());
			List<String> compareResult = Utility.compareMaps(expectedMap,
					actualMap);
			logger.info("Mismatch Values: " + compareResult);
			mismatchValues.addAll(compareResult);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return mismatchValues;
	}

	public List<Outlets> getUpdatedOutletsObjectForPOSTAPI(ExamplesTable fields) {
		List<Outlets> updatedOutletsObj = new ArrayList<>();
		String noOfOutlets = null;
		String outletType = null;
		String settType = null;
		String mcc = null;
		ArrayList<HashMap<String, Object>> expectedList = jsonUtil
				.getDataMap(fields);
		noOfOutlets = Integer.toString(expectedList.size());
		logger.info("No of Outlets in expected JSON file: " + noOfOutlets);
		for (int k = 0; k < expectedList.size(); k++) {
			HashMap<String, Object> fieldMap = expectedList.get(k);
			for (String key : fieldMap.keySet()) {
				System.out.println(key + " : " + fieldMap.get(key).toString());
				String keyValue = fieldMap.get(key).toString();
				if (key.equals("outletType"))
					outletType = keyValue;
				else if (key.equals("settlementType"))
					settType = keyValue;
				else if (key.equals("merchantCategoryCode"))
					mcc = keyValue;
			}

			// get the outlet json based on settlement type
			String outletReqJson = null;
			Outlet newOutletObj = new Outlet();
			if (settType.equals("DE_CENTRALIZED"))
				outletReqJson = "decentralizedOutletPost.json";
			else
				outletReqJson = "centralizedOutletPost.json";

			GetResponse getOutResponse = jsonUtil.readValue(outletReqJson,
					GetResponse.class);
			newOutletObj = getOutResponse.getOutlet();
			newOutletObj.setSequenceNumber(Integer.toString(k + 1));

			// update the mcc and sett type based on outlet sett type
			newOutletObj.setOutletType(outletType);
			newOutletObj.setMerchantCategoryCode(mcc);

			Outlets newOutletsObj = new Outlets();
			newOutletsObj.setOutlet(newOutletObj);

			updatedOutletsObj.add(newOutletsObj);
		}
		return updatedOutletsObj;
	}

	public List<Terminals> getUpdatedTerminalsObjectForPOSTAPI(
			String terminalType, String outletType) {
		// get Current date to create a new terminal
		String currentDate = DateUtility.getCurrentDate().toString();
		List<Terminals> updatedTerminalsObj = new ArrayList<>();
		String[] terminalTypes = { terminalType };

		if (terminalType.contains("-")) {
			terminalTypes = null;
			terminalTypes = terminalType.split("-");
		}

		for (int i = 0; i < terminalTypes.length; i++) {
			// get the terminal json based on terminal type
			String terminalReqJson = null;
			if (terminalTypes[i].equals("POS"))
				terminalReqJson = "posTerminalCreate.json";
			else if (terminalTypes[i].equals("ECOM"))
				terminalReqJson = "ecommTerminalCreate.json";
			else
				terminalReqJson = "qrTerminalCreate.json";

			Terminal newTerminalObj = new Terminal();
			GetResponse getOutResponse = jsonUtil.readValue(terminalReqJson,
					GetResponse.class);
			newTerminalObj = getOutResponse.getTerminal();

			newTerminalObj.setSequenceNumber(Integer.toString(i + 1));
			newTerminalObj.setCreationDate(currentDate);

			if (terminalTypes[i].contains("QR")) {
				ExtendedConfig extConfig = newTerminalObj.getExtendedConfig();
				if (outletType.equals("ECOMM")) {
					// set the qrtype=dynamic and deliverStickerTo=null for
					// ECOMM outlet
					extConfig.setQrtype("DYNAMIC");
					extConfig.setDeliverStickerTo(null);
				}
				// setting the extended config for network MASTERCARD-VISA
				String network = "MASTERCARD-VISA";
				String interchanges = utility
						.getInterchangesForQRTerminal(network);
				String qrStringDraft = utility.getQRDraftString(network);
				extConfig.setInterchanges(interchanges);
				extConfig.setQrCodeStringDraft(qrStringDraft);
				newTerminalObj.setExtendedConfig(extConfig);
			}

			Terminals newTerminalsObj = new Terminals();
			newTerminalsObj.setTerminal(newTerminalObj);

			updatedTerminalsObj.add(newTerminalsObj);
		}
		return updatedTerminalsObj;
	}

	public List<Outlets> getUpdatedOutletAndTerminalObjectForPOSTAPI(
			ExamplesTable fields) {
		List<Outlets> updatedOutletsObj = new ArrayList<>();
		String noOfOutlets = null;
		String outletType = null;
		String settType = null;
		String mcc = null;
		String terminalType = null;
		ArrayList<HashMap<String, Object>> expectedList = jsonUtil
				.getDataMap(fields);
		noOfOutlets = Integer.toString(expectedList.size());
		logger.info("No of Outlets in expected JSON file: " + noOfOutlets);
		for (int k = 0; k < expectedList.size(); k++) {
			HashMap<String, Object> fieldMap = expectedList.get(k);
			for (String key : fieldMap.keySet()) {
				System.out.println(key + " : " + fieldMap.get(key).toString());
				String keyValue = fieldMap.get(key).toString();
				if (key.equals("outletType"))
					outletType = keyValue;
				else if (key.equals("settlementType"))
					settType = keyValue;
				else if (key.equals("merchantCategoryCode"))
					mcc = keyValue;
				else if (key.equals("terminalType"))
					terminalType = keyValue;
			}

			// get the outlet json based on settlement type
			String outletReqJson = null;
			Outlet newOutletObj = new Outlet();
			if (settType.equals("DE_CENTRALIZED"))
				outletReqJson = "decentralizedOutletPost.json";
			else
				outletReqJson = "centralizedOutletPostAllFields.json";

			GetResponse getOutResponse = jsonUtil.readValue(outletReqJson,
					GetResponse.class);
			newOutletObj = getOutResponse.getOutlet();
			newOutletObj.setSequenceNumber(Integer.toString(k + 1));

			// update the mcc and sett type based on outlet sett type
			newOutletObj.setOutletType(outletType);
			newOutletObj.setMerchantCategoryCode(mcc);

			List<Terminals> terminals = getUpdatedTerminalsObjectForPOSTAPI(
					terminalType, outletType);

			Outlets newOutletsObj = new Outlets();
			newOutletsObj.setOutlet(newOutletObj);
			newOutletsObj.setTerminals(terminals);

			updatedOutletsObj.add(newOutletsObj);
		}
		return updatedOutletsObj;
	}

	public Response getDecryptedResponseForGetQRTerminalAPI(String resourceUri,
			String standard, String offSet, String limit, String terminalId) {
		RestAssured.baseURI = restConfiguration.getBaseURI();
		RestAssured.config = restConfiguration.getRestAssuredConfig();
		logger.info("Get Request :" + restConfiguration.getBaseURI()
				+ resourceUri);
		String bankCode = config.getBankCodeForEncryptionDecryption();
		String publicKeyFingerPrint = dbUtil.getPublicKeyForDecryptionAPI(
				bankCode, resourceUri);
		mtafTestContext.put("publicKeyFingerPrint", publicKeyFingerPrint);
		Response response = null;

		if (!terminalId.equals("NA")) {
			response = RestAssured
					.given()
					.when()
					.contentType(config.getContenttype())
					.queryParams(RestConstants.QR_TERMINAL_STD, standard,
							RestConstants.PARAM_TERMINAL_ID, terminalId)
					.headers(RestConstants.HEADER_BANK_CODE, bankCode,
							RestConstants.HEADER_PUBLIC_KEY_FINGERPRINT,
							publicKeyFingerPrint, RestConstants.HEADER_IV, "0",
							RestConstants.HEADER_DIGEST_ALGORITHM, "NONE",
							RestConstants.HEADER_ENCRYPTED_KEY,
							RestConstants.ENCRYPTED_KEY_SAMPLE_VALUE_STAGESA)
					.get(resourceUri);
		} else {
			response = RestAssured
					.given()
					.when()
					.contentType(config.getContenttype())
					.queryParams(RestConstants.QR_TERMINAL_STD, standard,
							RestConstants.PARAM_OFFSET, offSet,
							RestConstants.PARAM_LIMIT, limit)
					.headers(RestConstants.HEADER_BANK_CODE, bankCode,
							RestConstants.HEADER_PUBLIC_KEY_FINGERPRINT,
							publicKeyFingerPrint, RestConstants.HEADER_IV, "0",
							RestConstants.HEADER_DIGEST_ALGORITHM, "NONE",
							RestConstants.HEADER_ENCRYPTED_KEY,
							RestConstants.ENCRYPTED_KEY_SAMPLE_VALUE_STAGESA)
					.get(resourceUri);
		}

		return response;
	}

	public Response postDecryptRequest(String requestBody, String resourceUri) {
		RestAssured.baseURI = restConfiguration.getBaseURI();
		RestAssured.config = restConfiguration.getRestAssuredConfig();
		logger.info("Post Request :" + restConfiguration.getBaseURI()
				+ resourceUri);
		String bankCode = config.getBankCodeForEncryptionDecryption();
		String publicKeyFingerPrint = dbUtil.getPublicKeyForDecryptionAPI(
				bankCode, resourceUri);
		mtafTestContext.put("publicKeyFingerPrint", publicKeyFingerPrint);
		logger.info("Request Body :" + requestBody);
		Response response = null;

		if (resourceUri.contains("decrypt") || resourceUri.contains("encrypt")) {
			// adding condition for user id as header parameter
			if (DBUtils.apiUserIds.size() > 1) {
				response = RestAssured
						.given()
						.when()
						.contentType(config.getContenttype())
						.headers(RestConstants.HEADER_BANK_CODE, bankCode,
								RestConstants.HEADER_PUBLIC_KEY_FINGERPRINT,
								publicKeyFingerPrint, RestConstants.HEADER_IV,
								"0", RestConstants.HEADER_DIGEST_ALGORITHM,
								"NONE", RestConstants.HEADER_API_IDENTIFIER,
								"simulator", RestConstants.HEADER_API_USER_ID,
								RestConstants.STAGE_SA_API_USER)
						.body(requestBody).post(resourceUri);
			} else {
				response = RestAssured
						.given()
						.when()
						.contentType(config.getContenttype())
						.headers(RestConstants.HEADER_BANK_CODE, bankCode,
								RestConstants.HEADER_PUBLIC_KEY_FINGERPRINT,
								publicKeyFingerPrint, RestConstants.HEADER_IV,
								"0", RestConstants.HEADER_DIGEST_ALGORITHM,
								"NONE", RestConstants.HEADER_API_IDENTIFIER,
								"simulator").body(requestBody)
						.post(resourceUri);
			}

		} else {
			response = RestAssured.given().when()
					.contentType(config.getContenttype())
					.headers(RestConstants.HEADER_BANK_CODE, bankCode)
					.body(requestBody).post(resourceUri);
		}
		return response;
	}

	public Response getDecryptedResponseForGetAPI(String resourceUri) {
		RestAssured.baseURI = restConfiguration.getBaseURI();
		RestAssured.config = restConfiguration.getRestAssuredConfig();
		logger.info("Get Request :" + restConfiguration.getBaseURI()
				+ resourceUri);
		String bankCode = config.getBankCodeForEncryptionDecryption();
		String publicKeyFingerPrint = dbUtil.getPublicKeyForDecryptionAPI(
				bankCode, resourceUri);
		mtafTestContext.put("publicKeyFingerPrint", publicKeyFingerPrint);

		Response response = RestAssured
				.given()
				.when()
				.contentType(config.getContenttype())
				.headers(RestConstants.HEADER_BANK_CODE, bankCode,
						RestConstants.HEADER_PUBLIC_KEY_FINGERPRINT,
						publicKeyFingerPrint, RestConstants.HEADER_IV, "0",
						RestConstants.HEADER_DIGEST_ALGORITHM, "NONE",
						RestConstants.HEADER_ENCRYPTED_KEY,
						RestConstants.ENCRYPTED_KEY_SAMPLE_VALUE_STAGESA)
				.get(resourceUri);

		return response;
	}

	public String getBankCode() {
		String bankCode = config.getBankcode();
		if (mtafTestContext.get("StoryName") != null) {
			String storyName = mtafTestContext.get("StoryName");
			if (storyName.contains("Fees"))
				bankCode = Utility.getUserInstituteFromConfig(config,"feesInstitution");
			else if (storyName.contains("Reports"))
				bankCode = Utility.getUserInstituteFromConfig(config,"institutionReports");
			else if (storyName.equals("TransactionSearchesAPI_POS"))
				bankCode = Utility.getUserInstituteFromConfig(config,"institutionPOS");
			else if (storyName.equals("TransactionSearchesAPI_OfflineQR"))
				bankCode = Utility.getUserInstituteFromConfig(config,"qrInstitution");
			else if (storyName.equals("TransactionSearchesAPI_EDC"))
				bankCode = Utility.getUserInstituteFromConfig(config,"msfInstitution");
			else if (storyName.equals("TransactionSearchesAPI_DCF"))
				bankCode = Utility.getUserInstituteFromConfig(config,"msfWithDCFInstitution");
			
			if(bankCode.contains("["))
				bankCode = bankCode.substring(bankCode.indexOf("[") + 1, bankCode.indexOf("]"));
		}
		return bankCode;

	}

	public List<Outlets> getUpdatedOutletAndTerminalObjectForPOSTAPI(
			String requestjson) {

		String currentDate = DateUtility.getCurrentDate().toString();
		GetResponse getOutResponse = jsonUtil.readValue(requestjson,
				GetResponse.class);
		List<Outlets> lstOutlets = getOutResponse.getOutlets();
		List<Outlets> updatedOutletLst = new ArrayList<>();

		for (int i = 0; i < lstOutlets.size(); i++) {
			Outlet outletObj = new Outlet();
			outletObj = lstOutlets.get(i).getOutlet();
			outletObj.setSequenceNumber(Integer.toString(i + 1));

			Profile outletProf = outletObj.getProfile();
			OutletServiceSteps.outletNames.add(outletProf.getBusinessName());

			// updating terminal objects
			List<Terminals> updatedTerminalsLst = new ArrayList<>();
			List<Terminals> lstTerminals = lstOutlets.get(i).getTerminals();
			for (int j = 0; j < lstTerminals.size(); j++) {
				Terminal termObj = new Terminal();
				termObj = lstTerminals.get(j).getTerminal();
				termObj.setSequenceNumber(Integer.toString(j + 1));
				termObj.setCreationDate(currentDate);

				TerminalServiceSteps.terminalNames.add(termObj.getName());

				Terminals updatedTerminalsObj = new Terminals();
				updatedTerminalsObj.setTerminal(termObj);
				updatedTerminalsLst.add(updatedTerminalsObj);
			}

			Outlets updatedOutletsObj = new Outlets();
			updatedOutletsObj.setOutlet(outletObj);
			updatedOutletsObj.setTerminals(updatedTerminalsLst);

			updatedOutletLst.add(updatedOutletsObj);
		}
		return updatedOutletLst;
	}


	public TransactionsSearchesRequest getUpdatedRequestForTxnSearchesAPI(
			ExamplesTable fields) {

		TransactionsSearchesRequest txnSearchReq = new TransactionsSearchesRequest();
		HashMap<String, String> txnDetailsMap = new HashMap<>();

		ArrayList<HashMap<String, Object>> expectedList = jsonUtil
				.getDataMap(fields);
		for (int k = 0; k < expectedList.size(); k++) {

			segment = null;
			transactionMessagingSystem = null;
			valueAddedService = null;
			terminalId = null;
			networkCode = null;
			transactionCount = null;
			settlementStatus = null;
			transactionType = null;
			reqParams = null;

			HashMap<String, Object> fieldMap = expectedList.get(k);
			for (String key : fieldMap.keySet()) {
				System.out.println(key + " : " + fieldMap.get(key).toString());
				String keyValue = fieldMap.get(key).toString();
				switch (key) {
				case "segment":
					segment = keyValue;
					break;
				case "transactionMessagingSystem":
					transactionMessagingSystem = keyValue;
					break;
				case "valueAddedService":
					valueAddedService = keyValue;
					break;
				case "terminalId":
					terminalId = keyValue;
					break;
				case "networkCode":
					networkCode = keyValue;
					break;
				case "transactionCount":
					transactionCount = keyValue;
					break;
				case "settlementStatus":
					settlementStatus = keyValue;
					break;
				case "transactionType":
					transactionType = keyValue;
					break;
				case "reqParams":
					reqParams = keyValue;
					break;
				}
			}

			String bankCode = getBankCode();

			if (segment.equals("TRANSACTION")) {
				txnDetailsDBMapForTxnReq = dbUtil
						.getTxnSettlementDetailsBasedOnMode(bankCode,
								terminalId, networkCode, transactionCount,
								settlementStatus, transactionType);
				txnDetailsMap = (txnDetailsDBMapForTxnReq.isEmpty()) ? txnDetailsMap
						: txnDetailsDBMapForTxnReq.get(0);
				setTractionDetailsFromTxnSettlementForTxnReq(txnSearchReq,
						txnDetailsMap);
			} else if (segment.equals("AUTHORIZATION")) {
				txnDetailsDBMapForTxnReq = dbUtil.getAuthorizationDetails(
						bankCode, terminalId, transactionCount);
				txnDetailsMap = (txnDetailsDBMapForTxnReq.isEmpty()) ? txnDetailsMap
						: txnDetailsDBMapForTxnReq.get(0);
				setAuthDetailsFromTxnAcqMerchantForTxnReq(txnSearchReq,
						txnDetailsMap);
			}
		}
		return txnSearchReq;
	}

	public TransactionsSearchesResponse getTxnSearchesResponse(
			TransactionsSearchesRequest txnSearchReq) {

		List<HashMap<String, String>> txnDetailsDBMapForTxnRes = new ArrayList<>();
		String txnDate = null;
		
		if (segment.equals("TRANSACTION"))
			txnDate = "TRANSACTION_DATE";
		else if (segment.equals("AUTHORIZATION"))
			txnDate = "TRANSACTION_LOCAL_DATE_TIME_12";
		
		//get updated txnDetailsMapForRes based on txn Date
		for(int i=0;i<txnDetailsDBMapForTxnReq.size();i++){
			HashMap<String, String> innerMap = new HashMap<>();
			String txnDateFromDBMap = txnDetailsDBMapForTxnReq.get(i).get(txnDate).split(" ")[0];
			if(txnDateFromDBMap.equals(txnSearchReq.getTransactionDate())){
				innerMap = txnDetailsDBMapForTxnReq.get(i);
				txnDetailsDBMapForTxnRes.add(innerMap);
			}
		}
		
		// set page summary object
		PageSummary pageObj = new PageSummary();
		pageObj.setOffset(0);
		pageObj.setLimit(50); // set as 50
		pageObj.setCount(txnDetailsDBMapForTxnRes.size());
		pageObj.setTotal(txnDetailsDBMapForTxnRes.size());

		// set transactions searches output details
		List<Transactions> lstTransactions = new ArrayList<>();
		for (int i = 0; i < txnDetailsDBMapForTxnRes.size(); i++) {
			HashMap<String, String> txnDetailsMap = txnDetailsDBMapForTxnRes
					.get(i);
			Transactions transaction = new Transactions();

			if (segment.equals("TRANSACTION"))
				transaction = setTxnDetailsFromTxnSettForTxnResponse(
						txnDetailsMap, txnSearchReq);
			else if (segment.equals("AUTHORIZATION"))
				transaction = setTxnDetailsFromTxnAcqMerchForTxnResponse(
						txnDetailsMap, txnSearchReq);

			lstTransactions.add(transaction);
		}

		TransactionsSearchesResponse transactionsSearchesResponse = new TransactionsSearchesResponse();
		transactionsSearchesResponse.setPageSummary(pageObj);
		transactionsSearchesResponse.setTransactions(lstTransactions);

		return transactionsSearchesResponse;
	}

	public TransactionsSearchesRequest setTractionDetailsFromTxnSettlementForTxnReq(
			TransactionsSearchesRequest txnSearchReq,
			HashMap<String, String> txnDetails) {

		txnSearchReq.setSegment(segment);
		txnSearchReq.setTerminalId(terminalId);
		txnSearchReq.setLastNtransactions(transactionCount);
		txnSearchReq.setTransactionMessagingSystem(transactionMessagingSystem);
		txnSearchReq.setSentToInterchange("NO");
		txnSearchReq.setIncludeAssociatedTransactions("YES");
		txnSearchReq.setValueAddedService(valueAddedService);
		txnSearchReq.setResponseCode("000");
		txnSearchReq.setIssuerNetworkID(networkCode);
		txnSearchReq.setTransactionType(transactionType);

		settlementStatus = (settlementStatus != null) ? ConstantUtils
				.getSettlementStatus(settlementStatus) : settlementStatus;
		txnSearchReq.setSettlementStatus(settlementStatus);

		if (txnDetails.isEmpty())
			isTxnsPresent = false;
		else
			isTxnsPresent = true;

		if (isTxnsPresent) {
			String txnDate = txnDetails.get("TRANSACTION_DATE").split(" ")[0];
			txnSearchReq.setTransactionDate(txnDate);

			if(reqParams!=null){
				if(reqParams.equals("ALL")){
					
					txnSearchReq.setMerchantId(txnDetails.get("MERCHANT_NUMBER"));
					txnSearchReq.setOutletId(txnDetails.get("OUTLET_NUMBER"));
					
					txnSearchReq.setMicrofilmReferenceNumber(txnDetails
							.get("MICROFILM_REF_NUMBER"));
					txnSearchReq.setRetrievalReferenceNumber(txnDetails
							.get("REFERENCE_NUMBER"));

					txnSearchReq.setFromTransactionDate(txnDate + "T00:00:00");
					txnSearchReq.setToTransactionDate(txnDate + "T23:59:59");
					txnSearchReq.setTransactionType(txnDetails
							.get("TRANSACTION_CODE"));

					String txnCurrency = ConstantUtils
							.getCurrencyCodeInLettersFromDigits(txnDetails
									.get("TRANSACTION_CURRENCY"));
					txnSearchReq.setTransactionCurrency(txnCurrency);

					txnSearchReq.setMinimumTransactionAmount(txnDetails
							.get("TRANSACTION_AMOUNT"));
					txnSearchReq.setMaximumTransactionAmount(txnDetails
							.get("TRANSACTION_AMOUNT"));

					String reversalIndicator = txnDetails.get("REVERSAL_FLAG");
					reversalIndicator = (reversalIndicator.equals("O")) ? "ORIGINAL"
							: "REVERSAL";
					txnSearchReq.setReversalIndicator(reversalIndicator);

					txnSearchReq.setAuthorizationDestinationNetwork(txnDetails
							.get("ISSUER_NETWORK_CODE"));
					txnSearchReq.setClearingInterchange(txnDetails
							.get("ISSUER_NETWORK_CODE"));

					String clearingStatus = (txnDetails.get("CLEARING_DATE") == null) ? "PENDING"
							: "CLEARED";
					txnSearchReq.setClearingStatus(clearingStatus);

					txnSearchReq.setTraceAuditNumber(txnDetails
							.get("TRACE_AUDIT_NUMBER"));
					txnSearchReq.setCardNumber(txnDetails.get("CARD_NUMBER"));
					txnSearchReq.setTerminalType(txnDetails
							.get("TERMINAL_TYPE_124"));

					String onusFlag = txnDetails.get("BIN_TYPE");
					onusFlag = (onusFlag.equals("OFF")) ? "NO" : "YES";
					txnSearchReq.setOnusFlag(onusFlag);
					
				}
			}
		} else {
			txnSearchReq.setTransactionDate(DateUtility.getCurrentDate()
					.toString());
		}

		return txnSearchReq;
	}

	public TransactionsSearchesRequest setAuthDetailsFromTxnAcqMerchantForTxnReq(
			TransactionsSearchesRequest txnSearchReq,
			HashMap<String, String> txnDetails) {

		txnSearchReq.setSegment(segment);
		txnSearchReq.setTerminalId(terminalId);
		txnSearchReq.setLastNtransactions(transactionCount);
		txnSearchReq.setValueAddedService(valueAddedService);
//		txnSearchReq.setResponseCode("000");

		if (txnDetails.isEmpty())
			isTxnsPresent = false;
		else
			isTxnsPresent = true;

		if (isTxnsPresent) {
			txnSearchReq.setMerchantId(txnDetails.get("MERCHANT_NUMBER"));
			txnSearchReq
					.setOutletId(txnDetails.get("CARD_ACCEPTOR_ID_CODE_42"));

			String txnDate = txnDetails.get("TRANSACTION_LOCAL_DATE_TIME_12")
					.split(" ")[0];
			txnSearchReq.setTransactionDate(txnDate);
		} else {
			txnSearchReq.setTransactionDate(DateUtility.getCurrentDate()
					.toString());
		}

		return txnSearchReq;
	}

	public Transactions setTxnDetailsFromTxnSettForTxnResponse(
			HashMap<String, String> txnDetailsMap,
			TransactionsSearchesRequest txnSearchReq) {

		Transactions transaction = new Transactions();
		transaction.setSegment(segment);

		Authorization authorization = new Authorization();
		authorization.setMicrofilmReferenceNumber(txnDetailsMap
				.get("MICROFILM_REF_NUMBER"));
		authorization.setProcessingCode(txnDetailsMap.get("PROCESSING_CODE"));
		authorization.setFunctionCode(txnDetailsMap.get("FUNCTION_CODE"));
		authorization.setCardNumber(ConstantUtils
				.getCardNumberInEncryptedFormat(txnDetailsMap
						.get("CARD_NUMBER")));
		authorization.setTransactionType(txnDetailsMap.get("TRANSACTION_CODE"));

		String txnDate = txnDetailsMap.get("TRANSACTION_DATE");
		txnDate = (txnDate.replace(" ", "T"));
		authorization.setTransactionDateTime(txnDate);

		String reversalIndicator = txnDetailsMap.get("REVERSAL_FLAG");
		reversalIndicator = (reversalIndicator.equals("O")) ? "ORIGINAL"
				: "REVERSAL";
		authorization.setReversalIndicator(reversalIndicator);

		String tipAmount = txnDetailsMap.get("TIP_AMOUNT");
		tipAmount = (tipAmount!=null)?(tipAmount.equals("0")?"0":Utility.roundOffToTwoDecimal(tipAmount)):tipAmount;
		authorization.setTipAmount(tipAmount);
		
		authorization.setAuthorizationID(txnDetailsMap
				.get("AUTHORIZATION_CODE"));
		authorization.setTraceAuditNumber(txnDetailsMap
				.get("TRACE_AUDIT_NUMBER"));
		authorization.setRetrievalReferenceNumber(txnDetailsMap
				.get("REFERENCE_NUMBER"));
		authorization.setSourceChannel(txnDetailsMap.get("SOURCE_CHANNEL"));

		String transmissionDate = txnDetailsMap.get("TRANSMISSION_DATE_TIME");
		transmissionDate = (transmissionDate.replace(" ", "T"));
		authorization.setTransmissionDateTime(transmissionDate);

		authorization.setAcquirerInstitutionID(txnDetailsMap
				.get("ACQUIRER_BANK_CODE"));
		authorization.setForwardingInstitutionID(txnDetailsMap
				.get("FORWARDING_INSTITUTION_CODE"));
		authorization.setIssuerNetworkId(txnDetailsMap
				.get("ISSUER_NETWORK_CODE"));

		String termType = txnDetailsMap.get("TERMINAL_TYPE_124");
		String txnMode = txnDetailsMap.get("TRANSACTION_MODE");
		txnMode = (txnMode!=null)?((txnMode.equals("I"))?"INCOMING":"OUTGOING"):txnMode;
		authorization.setTransactionFlow(txnMode);

		authorization.setNetworkCardId(txnDetailsMap.get("NETWORK_CARD_ID"));
		authorization.setServiceCode(txnDetailsMap.get("SERVICE_CODE"));
		authorization.setUcafIndicator(txnDetailsMap.get("UCAF_IND"));
		authorization.setCardProgramNetworkId(txnDetailsMap
				.get("CARD_PROGRAM_NETWORK_CODE"));
		authorization.setAcquiringCountry(dbUtil.getCountryCode(txnDetailsMap
				.get("ACQUIRING_COUNTRY_CODE_19")));
		authorization.setIssuingCountry(dbUtil.getCountryCode(txnDetailsMap
				.get("ISSUING_COUNTRY_CODE_20")));
		authorization.setTransactionAmount(txnDetailsMap
				.get("TRANSACTION_AMOUNT"));
		authorization.setTransactionCurrency(ConstantUtils
				.getCurrencyCodeInLettersFromDigits(txnDetailsMap
						.get("TRANSACTION_CURRENCY")));

		if (transactionMessagingSystem == null){
			if (termType.equals("QRT"))
				transactionMessagingSystem = "SINGLE_MESSAGE";
			else
				transactionMessagingSystem = "DUAL_MESSAGE";
		}
		authorization.setTransactionMessagingSystem(transactionMessagingSystem);

		String authSourceNetwork = txnDetailsMap
				.get("SOURCE_INTERFACE_CODE");
		authSourceNetwork = (authSourceNetwork!=null)?(authSourceNetwork.substring(0,2)):authSourceNetwork;
		authorization.setAuthorizationSourceNetwork(authSourceNetwork);
		
		String destSourceNetwork = txnDetailsMap
				.get("DEST_INTERFACE_CODE");
		destSourceNetwork = (destSourceNetwork!=null)?(destSourceNetwork.substring(0,2)):destSourceNetwork;
		authorization.setAuthorizationDestinationNetwork(destSourceNetwork);
		
		String msgType = txnDetailsMap.get("REVERSAL_FLAG");
		msgType = (msgType.equals("O"))?"AUTHORIZATION":"REVERSAL";
		authorization.setMessageType(msgType);
		
		String sourceActNo = txnDetailsMap
				.get("CARD_ACCOUNT_NUMBER");
		sourceActNo = (sourceActNo!=null)?ConstantUtils.getCardNumberInEncryptedFormat(sourceActNo):sourceActNo;
		authorization.setSourceAccountNumber(sourceActNo);
		authorization.setDestinationAccountNumber(txnDetailsMap
				.get("DEST_ACCOUNT_NUMBER_103"));
		
		authorization.setResponseCode("000");
		authorization.setOnusFlag(txnDetailsMap.get("BIN_TYPE"));

		AmountDetails amountDetails = new AmountDetails();
		amountDetails.setBillingAmount(txnDetailsMap.get("BILLING_AMOUNT"));
		amountDetails.setBillingCurrency(txnDetailsMap.get("BILLING_CURRENCY"));
		amountDetails.setSettlementAmount(txnDetailsMap
				.get("SETTLEMENT_AMOUNT"));
		amountDetails.setSettlementCurrency(txnDetailsMap
				.get("SETTLEMENT_CURRENCY"));
		amountDetails.setTerminalCurrency(txnDetailsMap
				.get("TERMINAL_CURRENCY"));
		amountDetails.setOriginalterminalAmount(txnDetailsMap
				.get("TERMINAL_AMOUNT"));
		amountDetails.setCashbackAmount(txnDetailsMap.get("CASH_BACK_AMOUNT"));
		authorization.setAmountDetails(amountDetails);
		transaction.setAuthorization(authorization);

		Originator originator = new Originator();
		originator.setMerchantID(txnDetailsMap.get("MERCHANT_NUMBER"));
		originator.setMerchantBusinessName(txnDetailsMap
				.get("MERCHANT_ACRONYM"));

		MerchantAddress merchantAddress = new MerchantAddress();
		merchantAddress.setLine1(txnDetailsMap.get("MERCHANT_STREET_ADDRESS"));
		merchantAddress.setCity(txnDetailsMap.get("MERCHANT_CITY"));
		String stateCode = txnDetailsMap.get("MERCHANT_STATE");
		String countryCode = txnDetailsMap.get("MERCHANT_COUNTRY");
		merchantAddress.setState(dbUtil.getStateNameFromStateCode(stateCode,
				countryCode));
		merchantAddress.setCountry(dbUtil.getCountryCode(countryCode));
		merchantAddress.setZip(txnDetailsMap.get("MERCHANT_ZIP_CODE"));
		originator.setMerchantAddress(merchantAddress);

		originator.setOutletId(txnDetailsMap.get("OUTLET_NUMBER"));
		originator.setTerminalType(txnDetailsMap.get("TERMINAL_TYPE_124"));

		String catlvl = null;
		String preClearingSrc = txnDetailsMap.get("PRE_CLEARING_SOURCE");
		if (termType.equals("QRT"))
			catlvl = "6";
		else if(termType.equals("POS") && ((preClearingSrc!=null)?(preClearingSrc.contains("POS")?true:false):false))
			catlvl = "NA";
		originator.setCatLevel(catlvl);

		originator.setTerminalId(txnDetailsMap.get("TERMINAL_ID"));
		originator.setMcc(txnDetailsMap.get("MCC"));
		transaction.setOriginator(originator);

		OriginationDetails originationDetails = new OriginationDetails();
		originationDetails.setPosConditionCodeBase2(txnDetailsMap
				.get("POS_CONDITION_CODE_BASE2"));
		originationDetails.setPosData(txnDetailsMap.get("POS_DATA"));
		originationDetails.setPosEntryMode(txnDetailsMap
				.get("POS_ENTRY_MODE_BASE2"));
		originationDetails.setPosDataIPM(txnDetailsMap.get("POS_DATA_IPM"));
		originationDetails.setAdditionalData(txnDetailsMap
				.get("ADDITIONAL_DATA"));
		transaction.setOriginationDetails(originationDetails);

		ClearingDetails clearingDetails = new ClearingDetails();
		String sentToInterchange = (txnDetailsMap
				.get("INTERCHANGE_FILE_NUMBER") == null) ? "NO" : "YES";
		clearingDetails.setSentToInterchange(sentToInterchange);
		String outgoingFlag = txnDetailsMap.get("SETTLEMENT_PROC_FLAG_OUTG");
		clearingDetails.setClearingStatus(ConstantUtils.getInterchangeClearingStatusFromCode_API(outgoingFlag));
		transaction.setClearingDetails(clearingDetails);

		
		EmvData emvData = new EmvData();
		if(txnDetailsMap.get("EMVDATA")!=null){
			HashMap<String, String> emvDetails = dbUtil.getEMVDetailsBasedOnARN(txnDetailsMap.get("ACQUIRER_BANK_CODE"),
						txnDetailsMap.get("MICROFILM_REF_NUMBER"));
			if(!emvDetails.isEmpty()){
				emvData.setApplicationInterchangeProfile(emvDetails.get("APP_INTERCHG_PROFILE_82"));
				emvData.setTerminalVerificationResult(emvDetails.get("TERM_VER_RESULTS_95"));
				emvData.setTransactionDate(emvDetails.get("TERM_TRAN_DATE_9A"));
				emvData.setTransactionType(emvDetails.get("TRAN_TYPE_9C"));
				emvData.setCardSequenceNumber(emvDetails.get("CARD_SEQ_NUMBER_5F34"));
				emvData.setTerminalCapabilities(emvDetails.get("TERM_CAP_PROFILE_9F33"));
				emvData.setTerminalCountryCode(emvDetails.get("TERM_COUNTRY_CODE_9F1A"));
				emvData.setInterfaceDeviceSerialNumber(emvDetails.get("TERM_SERIAL_NUMBER_9F1E"));
				
				BigInteger authorizedAmount = new BigInteger(emvDetails.get("CRYPTOGRAM_AMOUNT_9F02"));
				BigDecimal authAmountWithDecimalPointUptoTwoDigits=new BigDecimal(authorizedAmount,2);
				String authAmt = Utility.removingAdditonalZeroesAfterDecimal(authAmountWithDecimalPointUptoTwoDigits.toString());
				emvData.setAuthorizedAmount(authAmt);
				
				emvData.setOtherAmount(emvDetails.get("OTHER_AMOUNT_9F03"));
				emvData.setTerminalApplicationVersionNumber(emvDetails.get("TERM_APP_VER_NUMBER_9F09"));
				emvData.setIssuerApplicationData(emvDetails.get("ISSUER_APP_DATA_9F10"));
				emvData.setCryptogramVersion(emvDetails.get("CRYPTOGRAM_VERSION_9F10"));
				emvData.setDerivationKeyIndex(emvDetails.get("DERIVATION_KEY_INDEX_9F10"));
				emvData.setCardVerificationResults(emvDetails.get("CARD_VER_RESULTS_9F10"));
				emvData.setTransactionCurrencyCode(emvDetails.get("TRANSACTION_CURRENCY_CODE_5F2A"));
				emvData.setDedicatedFileName(emvDetails.get("DEDICATED_FILE_NAME_84"));
				emvData.setApplicationCryptogram(emvDetails.get("CRYPTOGRAM_9F26"));
				emvData.setCryptogram(emvDetails.get("CRYPTOGRAM_INFO_DATA_9F27"));
				emvData.setCardholderVerificationMethod(emvDetails.get("CARDHOLDER_VERIF_METHOD_9F34"));
				emvData.setTerminalType(emvDetails.get("TERMINAL_TYPE_9F35"));
				emvData.setApplicationTransactionCounter(emvDetails.get("APP_TRAN_COUNTER_9F36"));
				emvData.setUnpredictableNumber(emvDetails.get("UNPREDICTABLE_NUMBER_9F37"));
				emvData.setTransactionSequenceNumber(emvDetails.get("TRANSACTION_SEQ_NO_9F41"));
				emvData.setTransactionCategoryCode(emvDetails.get("TRANSACTION_CATEGORY_CODE_9F53"));
				transaction.setEmvData(emvData);
			}
		}
		
		
		HashMap<String, String> merchSettDetails = new HashMap<>();
		String merchSettFlag = txnDetailsMap.get("MERCHANT_SETTLEMENT_FLAG");
		if(merchSettFlag.equals("Y")){
			merchSettDetails = dbUtil.getMerchSettlementDetailsBasedOnARN(
					txnDetailsMap.get("ACQUIRER_BANK_CODE"),
					txnDetailsMap.get("MICROFILM_REF_NUMBER"),
					reversalIndicator.substring(0, 1));
			String settCurrency = ConstantUtils
					.getCurrencyCodeInLettersFromDigits(merchSettDetails
							.get("MER_SETL_CURRENCY"));
			transaction.setSettlementCurrency(settCurrency);
			transaction.setCurrencyConversionRate(merchSettDetails
					.get("MER_SETL_CURRENCY_RATE"));
		}

		OnlineCreditDetails onlineCreditDetails = new OnlineCreditDetails();
		transaction.setOnlineCreditDetails(onlineCreditDetails);

		FeeDetails feeDetails = new FeeDetails();
		transaction.setFeeDetails(feeDetails);

		AuthorizationSurchargeConvenience authorizationSurchargeConvenience = new AuthorizationSurchargeConvenience();
		String surchAmount = txnDetailsMap.get("SURCHARGE_AMOUNT");
		surchAmount = (surchAmount!=null)?(!(surchAmount.equals("0"))?Utility.roundOffToTwoDecimal(surchAmount):surchAmount):surchAmount;
		authorizationSurchargeConvenience.setAmount(surchAmount);
		transaction.setAuthorizationSurchargeConvenience(authorizationSurchargeConvenience);

		SettlementSurchargeConvenience settlementSurchargeConvenience = new SettlementSurchargeConvenience();
		String surchAction = txnDetailsMap.get("SURCHARGE_ACTION");
		surchAction = (surchAction!=null)?((surchAction.equals("NC")) ? "NO_CREDIT_TO_MERCHANT": "CREDIT_TO_MERCHANT"):surchAction;
		settlementSurchargeConvenience.setAction(surchAction);
		
		if(txnDetailsMap.get("SURCHARGE_CURRENCY")!=null){
			String settSurchAmount = txnDetailsMap.get("SURCHARGE_AMOUNT");
			settSurchAmount = (settSurchAmount!=null)?(txnDetailsMap.get("SURCHARGE_ACTION").equals("NC")?"0":settSurchAmount):settSurchAmount;
			settlementSurchargeConvenience.setAmount(settSurchAmount);
		}
		if(txnDetailsMap.get("SURCHARGE_PLAN")!=null){
			String applyTax = dbUtil.getIfTaxesApplicableOnSurchargePlan(txnDetailsMap.get("ACQUIRER_BANK_CODE"),
					txnDetailsMap.get("SURCHARGE_PLAN"));
			if(applyTax.equals("Y")){
				settlementSurchargeConvenience.setServiceTax(txnDetailsMap.get("SURCHARGE_SERVICE_TAX"));
				settlementSurchargeConvenience.setAdditionalTax(txnDetailsMap.get("SURCHARGE_ADDITIONAL_TAX"));
			}
		}
		transaction.setSettlementSurchargeConvenience(settlementSurchargeConvenience);

		MerchantServiceFeeDetails merchantServiceFeeDetails = new MerchantServiceFeeDetails();
		if (!merchSettDetails.isEmpty()) {
			merchantServiceFeeDetails.setAction(merchSettDetails.get("MSF_ACTION"));
			merchantServiceFeeDetails.setWaiverStatus(ConstantUtils.getMSFWaiverStatusFromCode_API(merchSettDetails.get("WAIVE_MSF")));

			if(merchSettDetails.get("WAIVE_MSF").equals("NW") || merchSettDetails.get("WAIVE_MSF").equals("WV")){
				merchantServiceFeeDetails.setFromDate(merchSettDetails.get("MSF_FROM_DATE").split(" ")[0]);
				
				String msfToDate = merchSettDetails.get("MSF_TO_DATE");
				msfToDate = (msfToDate!=null)?(msfToDate.split(" ")[0]):msfToDate;
				merchantServiceFeeDetails.setToDate(msfToDate);
				
				String msfAmount = merchSettDetails.get("MSF_AMOUNT");
				msfAmount = (msfAmount!=null)?(Utility.roundOffToTwoDecimal(msfAmount)):msfAmount;
				merchantServiceFeeDetails.setAmount(msfAmount);
				
				String msfST = merchSettDetails.get("MSF_SERVICE_TAX");
				msfST = (msfST!=null)?(Utility.roundOffToTwoDecimal(msfST)):msfST;
				merchantServiceFeeDetails.setServiceTax(msfST);
				
				String msfAT = merchSettDetails.get("MSF_ADDITIONAL_TAX");
				msfAT = (msfAT!=null)?(Utility.roundOffToTwoDecimal(msfAT)):msfAT;
				merchantServiceFeeDetails.setAdditionalTax(msfAT);
			}
		}
		transaction.setMerchantServiceFeeDetails(merchantServiceFeeDetails);

		
		MerchantSettlementDetails merchantSettlementDetails = new MerchantSettlementDetails();
		if (!merchSettDetails.isEmpty()) {
			String settType = dbUtil.getSettlementTypeOfOutlet(
					txnDetailsMap.get("OUTLET_NUMBER"), "O");
			settType = (settType.equals("C")) ? "CENTRALIZED"
					: "DE_CENTRALIZED";
			merchantSettlementDetails.setSettlementType(settType);
			merchantSettlementDetails.setSettlementStatus(ConstantUtils
					.getSettlementStatus(txnDetailsMap.get("PAYMENT_STATUS")));
			
			String settAmount = Utility.convertStringToTwoDecimal(merchSettDetails.get("MER_SETL_AMOUNT"));
			
			//sett amounts are in negative for revresal and refund txns
			if(reversalIndicator.equals("REVERSAL") || txnDetailsMap.get("TRANSACTION_CODE").equals("20"))
				settAmount = "-"+settAmount;
	
			merchantSettlementDetails.setGrossSettlementAmount(settAmount);
			
			String netSettAmount = TransactionUtils.calculateNetSettlementAmount
					(authorization.getTransactionType(), reversalIndicator, authorization.getTransactionAmount(), 
							merchantServiceFeeDetails.getAmount(), merchantServiceFeeDetails.getServiceTax(), 
							merchantServiceFeeDetails.getAdditionalTax(), merchantServiceFeeDetails.getAction(), 
							merchSettDetails.get("WAIVE_MSF"), settlementSurchargeConvenience.getAmount(), 
							txnDetailsMap.get("SURCHARGE_ACTION"));
			
			merchantSettlementDetails.setNetSettlementAmount(netSettAmount);
		}
		transaction.setMerchantSettlementDetails(merchantSettlementDetails);
		

		StatementDetails statementDetails = new StatementDetails();
		if (!merchSettDetails.isEmpty()) {
			statementDetails.setStatementId(merchSettDetails
					.get("STATEMENT_NO"));
			
			String stmntDate = merchSettDetails.get("STATEMENT_DATE");
			stmntDate = (stmntDate!=null)?(stmntDate.split(" ")[0]):stmntDate;
			statementDetails.setStatementDate(stmntDate);
		}
		transaction.setStatementDetails(statementDetails);

		PaymentDetails paymentDetails = new PaymentDetails();
		if (!merchSettDetails.isEmpty()) {
			String paymntDate = merchSettDetails.get("PAYMENT_RELEASE_DATE");
			paymntDate = (paymntDate!=null)?(paymntDate.split(" ")[0]):paymntDate;
			paymentDetails.setPaymentReleaseDate(paymntDate);
		}
		transaction.setPaymentDetails(paymentDetails);

		return transaction;
	}

	public Transactions setTxnDetailsFromTxnAcqMerchForTxnResponse(
			HashMap<String, String> txnDetailsMap,
			TransactionsSearchesRequest txnSearchReq) {
		Transactions transaction = new Transactions();
		transaction.setSegment(segment);

		Authorization authorization = new Authorization();
		authorization
				.setProcessingCode(txnDetailsMap.get("PROCESSING_CODE_03"));
		authorization.setFunctionCode(txnDetailsMap.get("FUNCTION_CODE_24"));
		authorization.setCardNumber(ConstantUtils
				.getCardNumberInEncryptedFormat(txnDetailsMap
						.get("CARD_NUMBER_02")));
		authorization.setTransactionType(authorization.getProcessingCode()
				.substring(0, 2));

		String txnDate = txnDetailsMap.get("TRANSACTION_LOCAL_DATE_TIME_12");
		txnDate = (txnDate.replace(" ", "T"));
		authorization.setTransactionDateTime(txnDate);

		authorization.setAuthorizationID(txnDetailsMap
				.get("AUTHORIZATION_CODE_38"));
		authorization.setTraceAuditNumber(txnDetailsMap
				.get("TRACE_AUDIT_NUMBER_11"));
		authorization.setRetrievalReferenceNumber(txnDetailsMap
				.get("RETRIEVAL_REFERENCE_NUMBER_37"));
		authorization.setSourceChannel(txnDetailsMap.get("SOURCE_CHANNEL_123"));

		String transmissionDate = txnDetailsMap
				.get("TRANSMISSION_DATE_TIME_07");
		transmissionDate = (transmissionDate.replace(" ", "T"));
		authorization.setTransmissionDateTime(transmissionDate);

		authorization.setAcquirerInstitutionID(txnDetailsMap
				.get("ACQUIRE_INSTITUTION_ID_CODE_32"));
		authorization.setForwardingInstitutionID(txnDetailsMap
				.get("FORWARD_INSTITUTION_ID_CODE_33"));
		authorization.setIssuerNetworkId(txnDetailsMap
				.get("ISSUER_NETWORK_CODE"));
		
		String txnFlow = "OUTGOING";
		String termType = txnDetailsMap.get("TERMINAL_TYPE_124");
		if (termType.equals("QRT"))
			txnFlow = "INCOMING";
		authorization.setTransactionFlow(txnFlow);
		
		authorization.setNetworkCardId(txnDetailsMap.get("NETWORK_CARD_ID"));
		authorization.setServiceCode(txnDetailsMap.get("SERVICE_CODE_40"));
		authorization.setAcquiringCountry(dbUtil.getCountryCode(txnDetailsMap
				.get("ACQUIRING_COUNTRY_CODE_19")));
		authorization.setIssuingCountry(dbUtil.getCountryCode(txnDetailsMap
				.get("ISSUING_COUNTRY_CODE_20")));
		authorization.setTransactionAmount(txnDetailsMap
				.get("TRANSACTION_AMOUNT_04"));
		authorization.setTransactionCurrency(ConstantUtils
				.getCurrencyCodeInLettersFromDigits(txnDetailsMap
						.get("TRANSACTION_CURRENCY_CODE_49")));
		
		String authSourceNetwork = txnDetailsMap.get("SOURCE_INTERFACE_CODE");
		authSourceNetwork = (authSourceNetwork!=null)?(authSourceNetwork.substring(0, 2)):authSourceNetwork;
		authorization.setAuthorizationSourceNetwork(authSourceNetwork);
		
		authorization.setAuthorizationDestinationNetwork(txnDetailsMap.get(
				"DEST_INTERFACE_CODE").substring(0, 2));
		
		String sourceAcctNumber = txnDetailsMap.get("SOURCE_ACCOUNT_NUMBER_102");
		sourceAcctNumber = (sourceAcctNumber!=null)?ConstantUtils.getCardNumberInEncryptedFormat(sourceAcctNumber)
				:sourceAcctNumber;
		authorization.setSourceAccountNumber(sourceAcctNumber);
		authorization.setDestinationAccountNumber(txnDetailsMap.get("DEST_ACCOUNT_NUMBER_103"));
		
		authorization.setMessageType("AUTHORIZATION");
		authorization.setResponseCode(txnDetailsMap.get("ACTION_CODE_39"));
		authorization
				.setReasonCode(txnDetailsMap.get("MESSAGE_REASON_CODE_25"));
		authorization.setOnusFlag(txnDetailsMap.get("BIN_TYPE"));

		AmountDetails amountDetails = new AmountDetails();
		amountDetails.setBillingAmount(txnDetailsMap.get("BILLING_AMOUNT_06"));
		amountDetails.setBillingCurrency(txnDetailsMap
				.get("BILLING_CURRENCY_CODE_51"));
		amountDetails.setSettlementAmount(txnDetailsMap
				.get("SETTLEMENT_AMOUNT_05"));
		amountDetails.setSettlementCurrency(txnDetailsMap
				.get("SETTLEMENT_CURRENCY_CODE_50"));
		amountDetails.setTerminalCurrency(txnDetailsMap
				.get("TERMINAL_CURRENCY"));
		amountDetails.setOriginalterminalAmount(txnDetailsMap
				.get("TERMINAL_AMOUNT"));
		amountDetails.setCashbackAmount(txnDetailsMap
				.get("CASH_BACK_AMOUNT_54"));
		amountDetails.setReauthTransactionCount(txnDetailsMap
				.get("RE_AUTHORIZATION_COUNT"));
		amountDetails.setReauthTransactionAmount(txnDetailsMap
				.get("RE_AUTHORIZATION_TXN_AMOUNT"));
		authorization.setAmountDetails(amountDetails);
		transaction.setAuthorization(authorization);

		Originator originator = new Originator();
		originator.setMerchantID(txnDetailsMap.get("MERCHANT_NUMBER"));
		originator.setMerchantBusinessName(dbUtil
				.getMerchantNameFromDBUsingMerId(txnDetailsMap
						.get("MERCHANT_NUMBER")));
		originator.setOutletId(txnDetailsMap.get("CARD_ACCEPTOR_ID_CODE_42"));
		originator.setTerminalType(txnDetailsMap.get("TERMINAL_TYPE_124"));
		
		String catLvl = "NA";
		catLvl = (termType.equals("QRT"))?"6":catLvl;
		originator.setCatLevel(catLvl);
		
		originator.setTerminalId(txnDetailsMap.get("CARD_ACCEPTOR_TERM_ID_41"));
		originator.setMcc(txnDetailsMap.get("MCC_18"));
		transaction.setOriginator(originator);

		OriginationDetails originationDetails = new OriginationDetails();
		originationDetails.setPosData(txnDetailsMap.get("POS_DATA_CODE_22"));
		originationDetails.setAdditionalData(txnDetailsMap
				.get("ADDITIONAL_DATA_48"));
		transaction.setOriginationDetails(originationDetails);

		EmvData emvData = new EmvData();
		transaction.setEmvData(emvData);

		OnlineCreditDetails onlineCreditDetails = new OnlineCreditDetails();
		transaction.setOnlineCreditDetails(onlineCreditDetails);

		AuthorizationSurchargeConvenience authorizationSurchargeConvenience = new AuthorizationSurchargeConvenience();
		transaction
				.setAuthorizationSurchargeConvenience(authorizationSurchargeConvenience);

		SettlementSurchargeConvenience settlementSurchargeConvenience = new SettlementSurchargeConvenience();
		transaction
				.setSettlementSurchargeConvenience(settlementSurchargeConvenience);

		MerchantServiceFeeDetails merchantServiceFeeDetails = new MerchantServiceFeeDetails();
		transaction.setMerchantServiceFeeDetails(merchantServiceFeeDetails);

		return transaction;
	}
}
*/
