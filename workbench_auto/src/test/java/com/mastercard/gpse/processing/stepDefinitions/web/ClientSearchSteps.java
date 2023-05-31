package com.mastercard.gpse.processing.stepDefinitions.web;

import com.mastercard.gpse.processing.builders.ClientBuilder;
import com.mastercard.gpse.processing.managers.Report;
import com.mastercard.gpse.processing.pages.ClientPage;
import com.mastercard.gpse.processing.pages.CommonPage;
import com.mastercard.gpse.processing.pages.navigation.Navigator;
import com.mastercard.gpse.processing.utils.SoftAssert;
import com.mastercard.gpse.processing.utils.TestContext;
import com.mastercard.gpse.processing.utils.WaitUtility;
import com.mastercard.gpse.processing.workflowsapi.WBPreRequisite;
import com.mastercard.gpse.processing.workflowsui.ClientSearchWorkFlow;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class ClientSearchSteps {

    private static Logger logger = (Logger) LogManager.getLogger(ContractSearchSteps.class);

    private TestContext testContext;
    private SoftAssert softAssert;
    private ClientBuilder clientBuilder;

    public ClientSearchSteps(TestContext context) {
        this.testContext = context;
        this.softAssert = TestContext.softAssert;
    }

    @Then("user search client by client number")
    public void user_search_client_by_client_number() {
        clientBuilder = (ClientBuilder) WBPreRequisite.GlobalWBTestData.get(ClientBuilder.class.getSimpleName());
        new Navigator().navigateToLinks("By Number");
        new ClientPage().enterClientNumber(clientBuilder.getClientNumber());
        new ClientPage().clickOnFind();
        Report.info(logger, "searched client by clientNumber: " + clientBuilder.getClientNumber());
    }

    @Then("user search client by Identity Card Number")
    public void userSearchClientByIdentityCardNumber() {
        clientBuilder = (ClientBuilder) WBPreRequisite.GlobalWBTestData.get("ClientBuilder");
        new ClientPage().enterIdentityCardNumber(clientBuilder.getClientIdentificationData().getIdentificationDocumentDetails());
        new ClientPage().clickOnFind();
        Report.info(logger, "User search client by IdentityCardNumber");
    }

    @Then("user search client by Basic Search")
    public void user_search_client_by_basic_search() {
        clientBuilder = (ClientBuilder) WBPreRequisite.GlobalWBTestData.get("ClientBuilder");
        new ClientPage().enterBasicName(clientBuilder.getClientPersonalData().getFirstName());
        new ClientPage().clickOnFind();
        Report.info(logger, "User search client by IdentityCardNumber");
    }

    @Then("user search client by ClientID")
    public void user_search_client_by_client_id() {
        clientBuilder = (ClientBuilder) WBPreRequisite.GlobalWBTestData.get(ClientBuilder.class.getSimpleName());
        clientBuilder = new ClientBuilder();
        new ClientPage().enterClientID(clientBuilder.getClientID());
        new ClientPage().clickOnFind();
        Report.info(logger, "User search client by IdentityCardNumber");
    }

    @And("Validate Details Tab")
    public void validateDetailsTab() {
        softAssert.assertTrue(new ClientPage().getTextOfDetailsTab(), "Details tab validate Successful");
    }

    @Then("user search client by client number from different customer")
    public void userSearchClientByClientNumberFromDifferentCustomer() {
        boolean result;
        result = new ClientSearchWorkFlow().validateClientNumberFromDifferentCustomer();
        if (!result)
            softAssert.assertFalse(result, "Record not Found");
        clientBuilder = (ClientBuilder) WBPreRequisite.GlobalWBTestData.get("ClientBuilder");
        new ClientPage().enterClientNumber(clientBuilder.getClientNumber());
        new ClientPage().clickOnFind();
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        Report.info(logger, "User search client by IdentityCardNumber");
    }

    @And("user click on Form editor and click on Client Number")
    public void userClickOnFormEditorAndClickOnClientNumber() {
        new ClientPage().clickOnFormEditor();
        new WaitUtility().waitForTime(5000);
        new ClientPage().clickOnFieldClientNumber();
    }

    @Then("user validate Client Number is Hidden or Not")
    public void userValidateClientNumberIsHiddenOrNot() {
        new ClientPage().clickOnRadioBtnYes();
        new CommonPage().clickOnSaveBtn();
        new ClientPage().clickOnCrossButton();
        softAssert.assertFalse(new ClientPage().isClientNumberFieldDisplayed(), "Client Number is Hidden");
        new ClientPage().clickOnFormEditor();
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new ClientPage().clickOnFieldClientNumber();
        new ClientPage().clickOnRadioBtnNo();
        new CommonPage().clickOnSaveBtn();
        new ClientPage().clickOnCrossButton();
        softAssert.assertTrue(new ClientPage().isClientNumberFieldDisplayed(), "Client Number is not Hidden");
    }

}

