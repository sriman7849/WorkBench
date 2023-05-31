package com.mastercard.gpse.processing.stepDefinitions.web;

import com.mastercard.gpse.processing.builders.AccountBuilder;
import com.mastercard.gpse.processing.constants.BankConstants.INBANKConstants;
import com.mastercard.gpse.processing.constants.GlobalConstants;
import com.mastercard.gpse.processing.constants.MessageConstant;
import com.mastercard.gpse.processing.enums.FieldsEnum;
import com.mastercard.gpse.processing.enums.GroupEnum;
import com.mastercard.gpse.processing.enums.TabTitleEnum;
import com.mastercard.gpse.processing.managers.Report;
import com.mastercard.gpse.processing.pages.CommonPage;
import com.mastercard.gpse.processing.pages.ContractPage;
import com.mastercard.gpse.processing.pages.FieldPage;
import com.mastercard.gpse.processing.pages.InstalmentsPage;
import com.mastercard.gpse.processing.utils.*;
import com.mastercard.gpse.processing.workflowsapi.WBPreRequisite;
import com.mastercard.gpse.processing.workflowsui.AbstractWorkflow;
import com.mastercard.gpse.processing.workflowsui.ContractWorkFlow;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;

public class ContractMainSceenSteps extends AbstractWorkflow {

    private static final Logger logger = (Logger) LogManager.getLogger(ContractMainSceenSteps.class);
    TestContext testContext;
    SoftAssert softAssert;
    AccountBuilder contractBuilder;
    CommonPage commonPage;
    ContractPage contractPage;
    Map<String, String> financialLimitValue;

    public ContractMainSceenSteps(TestContext context) {
        testContext = context;
        this.softAssert = TestContext.softAssert;
    }

    @Then("validate Instalments Data Field")
    public void validateInstalmentsDataField() {
        contractBuilder = (AccountBuilder) WBPreRequisite.GlobalWBTestData.get(AccountBuilder.class.getSimpleName());
        String totalDueAmount = new CommonPage().getValueOf(TabTitleEnum.INSTALMENTS.getValue(), GroupEnum.TOTALS.getValue(), FieldsEnum.TOTAL_DUE_AMOUNT.getValue());
        String amount = contractBuilder.getContractFinancials().getTotalDueAmount();
        NumberFormat formatter = new DecimalFormat("0.00");
        String formatedNumber = formatter.format(Double.parseDouble(amount));
        Report.info(logger, totalDueAmount + " " + totalDueAmount);
        softAssert.assertEquals(totalDueAmount, formatedNumber, "Total Amount validated");
    }

    @Then("click on Instalment Plan By Balance")
    public void clickOnInstalmentPlanByBalance() {
        new InstalmentsPage().clickOnInstalmentPlanByBalanceButton();
    }

    @Then("click on Close Instalment Plan")
    public void clickOnCloseInstalmentPlan() {
        new InstalmentsPage().clickOnCloseInstalmentPlanButton();
    }

    @Then("validate Close Instalment Plans")
    public void validateCloseInstalmentPlans() {
        new InstalmentsPage().selectCloseInstalmentReasonCode("Cardholder request");
        new CommonPage().enterComment(GlobalConstants.CLOSE_INSTALMENT_COMMENT);
        new CommonPage().clickOnSaveBtn();
        softAssert.assertEquals(new CommonPage().getPopUpDialogText(), MessageConstant.CLOSE_INSTALMENT_SUCCESS_MSG, "Success Message validated");
    }

    @Then("Validate the contract main screen Financial Page")
    public void validateTheContractMainScreenFinancialPage() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        contractBuilder = (AccountBuilder) WBPreRequisite.GlobalWBTestData.get(AccountBuilder.class.getSimpleName());
        String contractName = new CommonPage().getValueOf(TabTitleEnum.FINANCIALS.getValue(), GroupEnum.GENERAL.getValue(), FieldsEnum.CONTRACT_NAME.getValue());
        softAssert.assertEquals(contractName, contractBuilder.getAccount().getContractName(), "Validate Contract Name successfully");
        Report.info(logger, "Validated Financial Page of Contract main Screen");
    }

    @And("validate the contract main screen details")
    public void validateTheContractMainScreenDetails() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        contractBuilder = (AccountBuilder) WBPreRequisite.GlobalWBTestData.get(AccountBuilder.class.getSimpleName());
        String contractName = new CommonPage().getValueOf(TabTitleEnum.DETAILS.getValue(), GroupEnum.GENERAL.getValue(), FieldsEnum.CONTRACT_NAME.getValue());
        softAssert.assertEquals(contractName, contractBuilder.getAccount().getContractName(), "Validate Contract Name successfully");
        Report.info(logger, "Validated contract main screen details");

    }

    @And("user validate contract main screen Full Data Fields")
    public void userValidateContractMainScreenFullDataFields() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        contractBuilder = (AccountBuilder) WBPreRequisite.GlobalWBTestData.get(AccountBuilder.class.getSimpleName());
        String contractName = new CommonPage().getValueOf(TabTitleEnum.DETAILS.getValue(), GroupEnum.GENERAL.getValue(), FieldsEnum.CONTRACT_NAME.getValue());
        softAssert.assertEquals(contractName, contractBuilder.getAccount().getContractName(), "Validate Contract Name successfully");
        String contractID = new CommonPage().getValueOf(TabTitleEnum.DETAILS.getValue(), GroupEnum.REFERENCE_DATA.getValue(), FieldsEnum.CONTRACT_ID.getValue());
        softAssert.assertEquals(contractID, contractBuilder.getContractFinancials().getContractID(), "Validate Contract ID successfully");
        String bankAccountNumber = new CommonPage().getValueOf(TabTitleEnum.DETAILS.getValue(), GroupEnum.GENERAL.getValue(), FieldsEnum.BANK_ACCOUNT_NUMBER.getValue());
        softAssert.assertEquals(bankAccountNumber, contractBuilder.getContractFinancials().getContractID(), "Validate Bank Account Number  successfully");

        Report.info(logger, "Validated contract main screen Full Data Fields");
    }

    @Then("click on Set {string} Limit")
    public void clickOnSetLimit(String tag) {
        new CommonPage().clickOnEditIconBtn();
        new ContractPage().clickOnSetAdditionalOrTemporaryFinancialLimit(tag);
    }


    @Then("validate {string} Financial Limit")
    public void validateFinancialLimit(String limit) {

        financialLimitValue=new ContractWorkFlow().setFinancialLimit(limit,GlobalConstants.FINANCIAL_LIMIT,DateUtility.plusDaysFromCurrentDate(0),DateUtility.plusDaysFromCurrentDate(2),GlobalConstants.REASON_DD_VALUE_BANK_DECISION,"Test" + RandomUtils.getRandomNumericString(10));
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_THREE_THOUSAND);
        if(limit.equalsIgnoreCase(GlobalConstants.ADDITIONAL))
            softAssert.assertEquals(financialLimitValue.get(GlobalConstants.MESSAGE),MessageConstant.SET_ADDITIONAL_FINANCIAL_LIMIT,MessageConstant.SET_ADDITIONAL_FINANCIAL_LIMIT);
        else
            softAssert.assertEquals(financialLimitValue.get(GlobalConstants.MESSAGE),MessageConstant.SET_TEMPORARY_FINANCIAL_LIMIT,MessageConstant.SET_TEMPORARY_FINANCIAL_LIMIT);
    }

    @Then("validate Credit Limit log data")
    public void validateCreditLimitLogData() {
        boolean flag=new ContractWorkFlow().validateCreditLimitLogData(financialLimitValue);
        softAssert.assertTrue(flag,"Credit Limit log data validated");
    }
}
