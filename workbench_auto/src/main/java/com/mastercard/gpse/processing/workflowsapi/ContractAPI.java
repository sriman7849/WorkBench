package com.mastercard.gpse.processing.workflowsapi;

import com.mastercard.gpse.processing.builders.ClientBuilder;
import com.mastercard.gpse.processing.configuration.ApiConfig;
import com.mastercard.gpse.processing.constants.BankConstants.INBANKConstants;
import com.mastercard.gpse.processing.constants.Constants;
import com.mastercard.gpse.processing.fileoperations.TestProperties;
import com.mastercard.gpse.processing.utils.DateUtility;
import com.mastercard.gpse.processing.utils.JsonUtils;
import com.mastercard.gpse.processing.utils.RandomUtils;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ContractAPI extends ApiConfig {

    private static final Logger logger = (Logger) LogManager.getLogger(CreateAccountAPI.class);

    public Response debitContract (String contractID, String body){
        String resourceUri = TestProperties.getInstance().getApiEndPointProperty("debitContract");
        resourceUri = resourceUri.replaceAll("\\{\\{accountID\\}\\}", contractID);
        resourceUri = resourceUri.replaceAll("\\{\\{requestID\\}\\}","INB" + DateUtility.getTimeStamp());

        Map<String, Object> mapHeaders = new HashMap<String,Object>();
        mapHeaders.put("Authorization", ApiConfig.getAuthHeaderForPost(body,resourceUri));
        Response postClientResponse = postRequest(apiContext.get("URI").toString(),body,mapHeaders);
        logger.info("Response::::"+postClientResponse.prettyPrint());
        return postClientResponse;
    }

    /**
     * Update the dynamic values of the debit contract payload
     *
     * @return - updated payload as JSONString
     */
    public String getModifiedPayload(){
        JSONObject createAccountReqBody = JsonUtils.parseJsonFileToJSONObject(Constants.API_DATA_FILE + "debitContract.json");
        JsonUtils.replaceValueInJSONObject(createAccountReqBody, "uniqueRefNumber", RandomUtils.getRandomNumericString(12));
        return createAccountReqBody.toString();
    }
}
