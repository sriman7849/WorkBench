package com.mastercard.gpse.processing.workflowsapi;

import com.mastercard.gpse.processing.builders.ClientBuilder;
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

public class CreateAccountAPI extends ApiConfig {

    private static final Logger logger = (Logger) LogManager.getLogger(CreateAccountAPI.class);

    /**
     * Post call to create client
     *
     * @param body - payload
     * @return - response of the API call
     */
    public Response createAccount(String body){

        String resourceUri = TestProperties.getInstance().getApiEndPointProperty("create.account");
        resourceUri = resourceUri.replaceAll("\\{\\{requestID\\}\\}","INB" + DateUtility.getTimeStamp());

        Map<String, Object> mapHeaders = new HashMap<String,Object>();
        mapHeaders.put("Authorization", ApiConfig.getAuthHeaderForPost(body,resourceUri));
        Response postClientResponse = postRequest(apiContext.get("URI").toString(),body,mapHeaders);
        logger.info("Response::::"+postClientResponse.prettyPrint());
        return postClientResponse;
    }

    /**
     * Update the dynamic values of the create account payload
     *
     * @return - updated payload as JSONString
     */
    public String getModifiedPayload(){
        ClientBuilder clientBuilder = (ClientBuilder) WBPreRequisite.GlobalWBTestData.get(ClientBuilder.class.getSimpleName());


        JSONObject createAccountReqBody = JsonUtils.parseJsonFileToJSONObject(Constants.API_DATA_FILE + "createAccount.json");

        JSONObject account = JsonUtils.getJSONObject(createAccountReqBody, "account");
        JsonUtils.replaceValueInJSONObject(account, "accountContractNumber", RandomUtils.getRandomNumericString(10));
        JsonUtils.replaceValueInJSONObject(account, "cbsNumber", RandomUtils.getRandomNumericString(10));
        JsonUtils.replaceValueInJSONObject(account, "productCode", INBANKConstants.AccountproductCode);

        JsonUtils.replaceValueInJSONObject(createAccountReqBody, "clientID", clientBuilder.getClientID());

        return createAccountReqBody.toString();
    }

    /**
     * @String accountID
     * @return - response of the API call
     * */
    public Response getContractFinancials(String accountID){

        String resourceUri = TestProperties.getInstance().getApiEndPointProperty("get.ContractFinancials");
        resourceUri = resourceUri.replaceAll("\\{\\{accountID\\}\\}", accountID);
        resourceUri = resourceUri.replaceAll("\\{\\{requestID\\}\\}", "INB" + DateUtility.getTimeStamp());
        Map<String, Object> mapHeaders = new HashMap<String,Object>();
        mapHeaders.put("Authorization", ApiConfig.getAuthHeaderForGet(resourceUri));
        Response getClientResponse = getRequest(apiContext.get("URI").toString(), ContentType.JSON,mapHeaders);
        logger.info("Response::::"+getClientResponse.prettyPrint());

        return getClientResponse;
    }
}
