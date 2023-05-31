package com.mastercard.gpse.processing.stepDefinitions.web;

import com.mastercard.gpse.processing.builders.AccountBuilder;
import com.mastercard.gpse.processing.builders.CardBuilder;
import com.mastercard.gpse.processing.enums.FieldsEnum;
import com.mastercard.gpse.processing.enums.GroupEnum;
import com.mastercard.gpse.processing.enums.TabTitleEnum;
import com.mastercard.gpse.processing.managers.Report;
import com.mastercard.gpse.processing.pages.CommonPage;
import com.mastercard.gpse.processing.pages.ContractsSearchPage;
import com.mastercard.gpse.processing.pages.RiskControlsPage;
import com.mastercard.gpse.processing.utils.SoftAssert;
import com.mastercard.gpse.processing.utils.TestContext;
import com.mastercard.gpse.processing.utils.WaitUtility;
import com.mastercard.gpse.processing.workflowsapi.WBPreRequisite;
import com.mastercard.gpse.processing.workflowsui.AbstractWorkflow;
import com.mastercard.gpse.processing.workflowsui.ContractSearchWorkFlow;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.Assert;

import java.util.HashMap;

public class ContractSearchSteps extends AbstractWorkflow {

    private static Logger logger = (Logger) LogManager.getLogger(ContractSearchSteps.class);
    TestContext testContext;
    SoftAssert softAssert;

    CardBuilder cardBuilder =new CardBuilder();
    ContractSearchWorkFlow contractSearchWorkFlow= new ContractSearchWorkFlow();
    HashMap<String, String> inputMap = new HashMap<>();
    AccountBuilder accountBuilder = new AccountBuilder();
    RiskControlsPage riskControlsPage =new RiskControlsPage();

    public ContractSearchSteps(TestContext context) {
        this.testContext = context;
        this.softAssert = TestContext.softAssert;
    }

    @Then("user search contract by contract ID")
    public void userSearchContractByContractID() {
        accountBuilder = (AccountBuilder) WBPreRequisite.GlobalWBTestData.get(AccountBuilder.class.getSimpleName());
        new ContractsSearchPage().clickOnByContractIDTab();
        new ContractsSearchPage().enterContractID(accountBuilder.getContractFinancials().getContractID());
        new ContractsSearchPage().clickOnFind();
        Report.info(logger, "searched contract by contractID: " + accountBuilder.getContractFinancials().getContractID());
    }

    @Then("user search contract by contract ID - {string}")
    public void userSearchContractByContractID(String contractID) {
        new ContractsSearchPage().clickOnByContractIDTab();
        new ContractsSearchPage().enterContractID(contractID);
        new ContractsSearchPage().clickOnFind();
        Report.info(logger, "Searched contract by contractID: " + contractID);
    }

    @Then("validate Risk Control {string} tab")
    public void validateRiskControlTabTitle(String txt) {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        riskControlsPage = pageObjectFactory.getPage(RiskControlsPage.class);
        softAssert.assertEquals(txt.toUpperCase(), riskControlsPage.getTabTitleText());
        logger.info("Validated " + txt);
    }

    @Then("user search contract by contract Number")
    public void userSearchContractByContractNumber() {
        accountBuilder = (AccountBuilder) WBPreRequisite.GlobalWBTestData.get(AccountBuilder.class.getSimpleName());
        new ContractsSearchPage().enterContractNumber(accountBuilder.getAccount().getAccountContractNumber());
        new ContractsSearchPage().clickOnFind();
        Report.info(logger, "searched contract by contract Number: " + accountBuilder.getAccount().getAccountContractNumber());
    }

    @Then("user search contract by contract Number - {string}")
    public void userSearchContractByContractNumber(String contractNumber) {
        new ContractsSearchPage().enterContractNumber(contractNumber);
        new ContractsSearchPage().clickOnFind();
        Report.info(logger, "Searched contract by contract Number: " + contractNumber);
    }

    @Then("user search contract by CBS Number")
    public void userSearchContractByCBSNumber() {
        cardBuilder = (CardBuilder) WBPreRequisite.GlobalWBTestData.get(CardBuilder.class.getSimpleName());
        new ContractsSearchPage().enterCBSNumber(cardBuilder.getCBSNumber());
        new ContractsSearchPage().clickOnFind();
        Report.info(logger, "searched contract by cbsNumber: " + cardBuilder.getCBSNumber());

    }

    @Then("validate Financial Details page")
    public void validateFinancialDetailsPage() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        accountBuilder = (AccountBuilder) WBPreRequisite.GlobalWBTestData.get(AccountBuilder.class.getSimpleName());
        String contractName = new CommonPage().getValueOf(TabTitleEnum.FINANCIALS.getValue(), GroupEnum.GENERAL.getValue(), FieldsEnum.CONTRACT_NAME.getValue());
        softAssert.assertEquals(contractName, accountBuilder.getAccount().getContractName(), "Validate Contract Name successfully");
        Report.info(logger, "Validated Financial Page of Contract main Screen");
    }

    @And("get the Bank Code to search in Contract Search Page")
    public void getBankCode() {
        String bankCode = new ContractsSearchPage().getBankCodeFromContractScreen();
        accountBuilder = (AccountBuilder) WBPreRequisite.GlobalWBTestData.get(AccountBuilder.class.getSimpleName());
        accountBuilder.setBankCode(bankCode);
        WBPreRequisite.GlobalWBTestData.put(AccountBuilder.class.getSimpleName(), accountBuilder);
    }

    @Then("user validate contract by Bank Account Number")
    public void userSearchContractByBankAccountNumber() {
        accountBuilder = (AccountBuilder) WBPreRequisite.GlobalWBTestData.get(AccountBuilder.class.getSimpleName());
        int rowCount;
        boolean result= false;
        rowCount = contractSearchWorkFlow.validateContractSearchByBankAccountNumber(accountBuilder.getContractFinancials().getContractID(), accountBuilder.getBankCode());
        if (rowCount>=1) {
            result = true;
            softAssert.assertTrue(result, "Record Found");
            Report.info(logger, "Record Found.");
        } else {
            softAssert.assertFalse(result, "Record Not Found");
            Report.info(logger, "Record Not Found.");
        }
    }
}



