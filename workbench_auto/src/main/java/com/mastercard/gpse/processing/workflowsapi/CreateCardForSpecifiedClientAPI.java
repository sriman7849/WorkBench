package com.mastercard.gpse.processing.workflowsapi;

import com.mastercard.gpse.processing.configuration.ApiConfig;
import com.mastercard.gpse.processing.constants.BankConstants.INBANKConstants;
import com.mastercard.gpse.processing.constants.Constants;
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

public class CreateCardForSpecifiedClientAPI extends ApiConfig {

    private static final Logger logger = (Logger) LogManager.getLogger(CreateCardForSpecifiedClientAPI.class);

    /**
     * Post call to create client
     *
     * @param body - payload
     * @return - response of the API call
     */
    public Response createCardForSpecifiedClient(String clientID, String accountID, String body ){

        String resourceUri = TestProperties.getInstance().getApiEndPointProperty("create.CardForSpecifiedClient");
        resourceUri = resourceUri.replaceAll("\\{\\{clientID\\}\\}",clientID);
        resourceUri = resourceUri.replaceAll("\\{\\{accountID\\}\\}",accountID);
        resourceUri = resourceUri.replaceAll("\\{\\{requestID\\}\\}","INB" + DateUtility.getTimeStamp());

        Map<String, Object> mapHeaders = new HashMap<String,Object>();
        mapHeaders.put("Authorization", ApiConfig.getAuthHeaderForPost(body,resourceUri));
        Response postClientResponse = postRequest(apiContext.get("URI").toString(),body,mapHeaders);
        logger.info("Response::::"+postClientResponse.prettyPrint());
        return postClientResponse;
    }
    //activate.card
    /**
     * Post call to create client
     *
     * @param body - payload
     * @return - response of the API call
     */
    public Response activateCard(String body, String cardID){

        String resourceUri = TestProperties.getInstance().getApiEndPointProperty("activate.card");
        resourceUri = resourceUri.replaceAll("\\{\\{cardID\\}\\}", cardID);
        resourceUri = resourceUri.replaceAll("\\{\\{requestID\\}\\}", "INB" + DateUtility.getTimeStamp());

        Map<String, Object> mapHeaders = new HashMap<String,Object>();
        mapHeaders.put("Authorization", ApiConfig.getAuthHeaderForPost(body,resourceUri));
        Response postClientResponse = postRequest(apiContext.get("URI").toString(),body,mapHeaders);
        logger.info("Response::::"+postClientResponse.prettyPrint());
        return postClientResponse;
    }

    /**
     * @String cardID
     * @return - response of the API call
     * */
    public Response getCardPlastics(String cardID){

        String resourceUri = TestProperties.getInstance().getApiEndPointProperty("get.CardPlastics");
        resourceUri = resourceUri.replaceAll("\\{\\{cardID\\}\\}", cardID);
        resourceUri = resourceUri.replaceAll("\\{\\{requestID\\}\\}", "INB" + DateUtility.getTimeStamp());
        Map<String, Object> mapHeaders = new HashMap<String,Object>();
        mapHeaders.put("Authorization", ApiConfig.getAuthHeaderForGet(resourceUri));
        Response getClientResponse = getRequest(apiContext.get("URI").toString(), ContentType.JSON,mapHeaders);
        logger.info("Response::::"+getClientResponse.prettyPrint());

        return getClientResponse;
    }

    public String getModifiedPayload(){

        String firstName = RandomUtils.getRandomFirstName();
        String lastName = RandomUtils.getRandomLastName();

        JSONObject createCardReqBody = JsonUtils.parseJsonFileToJSONObject(Constants.API_DATA_FILE + "createCardForSpecifiedClient.json");
        JSONObject card = JsonUtils.getJSONObject(createCardReqBody, "card");
        JsonUtils.replaceValueInJSONObject(card, "cardContractNumber", "");
        JsonUtils.replaceValueInJSONObject(card, "contractName", "CONTRACT" + firstName);
        JSONObject embossedData = JsonUtils.getJSONObject(card, "embossedData");
        JsonUtils.replaceValueInJSONObject(embossedData, "embossedCompanyName", "TURBINA" + firstName);
        JsonUtils.replaceValueInJSONObject(embossedData, "embossedFirstName", firstName);
        JsonUtils.replaceValueInJSONObject(embossedData, "embossedLastName", lastName);
        JsonUtils.replaceValueInJSONObject(embossedData, "embossedTitle", RandomUtils.getRandomNumericString(6));
        JsonUtils.replaceValueInJSONObject(card, "productCode", INBANKConstants.CardProductCode);

        return createCardReqBody.toString();
    }
}
