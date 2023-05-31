package com.mastercard.gpse.processing.workflowsapi;

import com.mastercard.gpse.processing.fileoperations.TestProperties;
import com.mastercard.gpse.processing.configuration.ApiConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.HashMap;
import java.util.Map;

public class GetClientAPI extends ApiConfig{
    private static final Logger logger = (Logger) LogManager.getLogger(GetClientAPI.class);

    public Response getClient(String clientClassifier){

        String resourceUri = TestProperties.getInstance().getApiEndPointProperty("get.client");
        resourceUri = resourceUri.replace("{clientClassifier}",clientClassifier);
        Map<String, Object> mapHeaders = new HashMap<String,Object>();
        mapHeaders.put("Authorization", ApiConfig.getAuthHeaderForGet(resourceUri));
        Response getClientResponse = getRequest(apiContext.get("URI").toString(), ContentType.JSON,mapHeaders);
        return getClientResponse;
    }



}
