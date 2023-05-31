package com.mastercard.gpse.processing.workflowsapi;

import com.mastercard.gpse.processing.configuration.ApiConfig;
import com.mastercard.gpse.processing.constants.Constants;
import com.mastercard.gpse.processing.constants.BankConstants.INBANKConstants;
import com.mastercard.gpse.processing.fileoperations.TestProperties;
import com.mastercard.gpse.processing.utils.DateUtility;
import com.mastercard.gpse.processing.utils.JsonUtils;
import com.mastercard.gpse.processing.utils.RandomUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateClientAPI extends ApiConfig {
    private static final Logger logger = (Logger) LogManager.getLogger(CreateClientAPI.class);

    /**
     * Post call to create client
     *
     * @param body - payload
     * @return - response of the API call
     */
    public Response createClient(String body){

        String resourceUri = TestProperties.getInstance().getApiEndPointProperty("create.client");
        resourceUri = resourceUri.replaceAll("\\{\\{requestID\\}\\}","INB" + DateUtility.getTimeStamp());

        Map<String, Object> mapHeaders = new HashMap<String,Object>();
        mapHeaders.put("Authorization", ApiConfig.getAuthHeaderForPost(body,resourceUri));
        Response postClientResponse = postRequest(apiContext.get("URI").toString(),body,mapHeaders);
        logger.info("Response::::"+postClientResponse.prettyPrint());
        return postClientResponse;
    }

    /**
     * Update the dynamic values of the create client payload
     *
     * @return - updated payload as JSONString
     */
    public String getModifiedPayload() {
        String firstName = RandomUtils.getRandomFirstName();
        String lastName = RandomUtils.getRandomLastName();

        JSONObject postOrderReqBody = JsonUtils.parseJsonFileToJSONObject(Constants.API_DATA_FILE + "createClient.json");
        JSONObject clientBaseAddressData = JsonUtils.getJSONObject(postOrderReqBody, "clientBaseAddressData");
        JsonUtils.replaceValueInJSONObject(clientBaseAddressData, "postalCode", RandomUtils.getRandomNumericString(6));

        JSONObject clientIdentificationData = JsonUtils.getJSONObject(postOrderReqBody, "clientIdentificationData");
        JsonUtils.replaceValueInJSONObject(clientIdentificationData, "identificationDocumentDetails", RandomUtils.getRandomNumericString(10));
        JsonUtils.replaceValueInJSONObject(clientIdentificationData, "identificationDocumentNumber", RandomUtils.getRandomNumericString(10));
        JsonUtils.replaceValueInJSONObject(clientIdentificationData, "identificationDocumentType", RandomUtils.getRandomNumericString(10));
        JsonUtils.replaceValueInJSONObject(clientIdentificationData, "socialNumber", RandomUtils.getRandomNumericString(10));
        JsonUtils.replaceValueInJSONObject(clientIdentificationData, "taxpayerIdentifier", RandomUtils.getRandomNumericString(10));

        JsonUtils.replaceValueInJSONObject(postOrderReqBody, "clientNumber", RandomUtils.getRandomNumericString(10));

        JSONObject clientPersonalData = JsonUtils.getJSONObject(postOrderReqBody, "clientPersonalData");
        JsonUtils.replaceValueInJSONObject(clientPersonalData, "firstName", firstName);
        JsonUtils.replaceValueInJSONObject(clientPersonalData, "lastName", lastName);

        JsonUtils.replaceValueInJSONObject(postOrderReqBody, "clientType", INBANKConstants.clientType);

        JSONObject embossedData = JsonUtils.getJSONObject(postOrderReqBody, "embossedData");
        JsonUtils.replaceValueInJSONObject(embossedData, "embossedFirstName", "EMB" + firstName);
        JsonUtils.replaceValueInJSONObject(embossedData, "embossedLastName", "EMB" + lastName);

        return postOrderReqBody.toString();
    }

    /**
     * @String ClientID
     * @return - response of the API call
     * */
    public Response getClientDetails(String clientID,String bankPrefix){

        String resourceUri = TestProperties.getInstance().getApiEndPointProperty("get.ClientDetails");
        resourceUri = resourceUri.replaceAll("\\{\\{clientID\\}\\}", clientID);
        resourceUri = resourceUri.replaceAll("\\{\\{requestID\\}\\}", bankPrefix + DateUtility.getTimeStamp());
        Map<String, Object> mapHeaders = new HashMap<String,Object>();
        mapHeaders.put("Authorization", ApiConfig.getAuthHeaderForGet(resourceUri));
        Response getClientDetailsResponse = getRequest(apiContext.get("URI").toString(), ContentType.JSON,mapHeaders);
        logger.info("Response::::"+getClientDetailsResponse.prettyPrint());

        return getClientDetailsResponse;
    }

    /**
     * @String clientID
     * @return - response of the API call
     * */
    public Response getCardsByClient(String clientID,String bankPrefix){

        String resourceUri = TestProperties.getInstance().getApiEndPointProperty("get.CardsByClients");
        resourceUri = resourceUri.replaceAll("\\{\\{clientID\\}\\}", clientID);
        resourceUri = resourceUri.replaceAll("\\{\\{requestID\\}\\}", bankPrefix + DateUtility.getTimeStamp());
        Map<String, Object> mapHeaders = new HashMap<String,Object>();
        mapHeaders.put("Authorization", ApiConfig.getAuthHeaderForGet(resourceUri));
        Response getClientResponse = getRequest(apiContext.get("URI").toString(), ContentType.JSON,mapHeaders);
        logger.info("Response::::"+getClientResponse.prettyPrint());

        return getClientResponse;
    }

}
