package com.mastercard.gpse.processing.stepDefinitions.api;

import com.mastercard.gpse.processing.constants.Constants;
import com.mastercard.gpse.processing.domain.RequestResponseParams;
import com.mastercard.gpse.processing.utils.FileUtility;
import com.mastercard.gpse.processing.utils.JsonUtils;
import com.mastercard.gpse.processing.utils.RestUtils;
import com.mastercard.gpse.processing.workflowsapi.CreateClientAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;


public class CreateClientSteps {

    private static final Logger logger = (Logger) LogManager.getLogger(CreateClientSteps.class);

    public CreateClientAPI createClientWorkFlow;

    //@Autowired
    RequestResponseParams params;
    @Autowired
    RestUtils restutil;

    @Given("User must have {string} body json")
    public void userHasValidjson(String apibody) {
        params = new RequestResponseParams();
        Assert.assertTrue(FileUtility.checkFileExist(Constants.API_DATA_FILE + apibody));
    }

    @When("User sent post request to {string}")
    public void userSendPostRequest(String requestapi) {
        String postOrderReqBody = JsonUtils.parseJsonFileToJSONString(Constants.API_DATA_FILE + requestapi);
        createClientWorkFlow = new CreateClientAPI();
        params.setResponse(createClientWorkFlow.createClient(postOrderReqBody));
    }

    @Then("User validate response code is {string} and validate {string} and {string} is present")
    public void userValidateResponse(String responseCode, String filedName, String filedValue) {

    }

    @Then("user get {string} response code")
    public void userValidateResponsecode(String rescode) {
        System.out.println(params.getResponse().asString());
        RestUtils.validateResponseCode(params.getResponse(), rescode);
    }
}
