package com.mastercard.gpse.processing.stepDefinitions.web;

import com.mastercard.gpse.processing.constants.BankConstants.INBANKConstants;
import com.mastercard.gpse.processing.constants.GlobalConstants;
import com.mastercard.gpse.processing.constants.MessageConstant;
import com.mastercard.gpse.processing.enums.FieldsEnum;
import com.mastercard.gpse.processing.enums.GroupEnum;
import com.mastercard.gpse.processing.managers.Report;
import com.mastercard.gpse.processing.pages.CommonPage;
import com.mastercard.gpse.processing.pages.ContractPage;
import com.mastercard.gpse.processing.utils.SoftAssert;
import com.mastercard.gpse.processing.utils.TestContext;
import com.mastercard.gpse.processing.utils.WaitUtility;
import com.mastercard.gpse.processing.workflowsui.AbstractWorkflow;
import com.mastercard.gpse.processing.workflowsui.ContractWorkFlow;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class ContractSteps extends AbstractWorkflow {
    private static Logger logger = (Logger) LogManager.getLogger(ContractSteps.class);

    private TestContext testContext;
    private SoftAssert softAssert;
    ContractWorkFlow contractWorkFlow = new ContractWorkFlow();

    public ContractSteps(TestContext testContext) {
        this.testContext = testContext;
        this.softAssert = TestContext.softAssert;
    }

    @Then("update Contract Parameters {string}")
    public void updateContractParameters(String contractParameter) {
        String msg = contractWorkFlow.updateContractParameter(contractParameter);
        softAssert.assertEquals(msg, MessageConstant.SET_PARAMETER_SUCCESS_MSG, "Contract Parameters updated");
    }

    @Then("Validate Contract Parameters History")
    public void validateContractParametersHistory() {
        boolean flag = contractWorkFlow.validateUpdateContractParameterHistory();
        softAssert.assertTrue(flag, "Updated Contract Parameters available in History tab");
    }

    @Then("validate Contract Parameters log")
    public void validateContractParametersLog() {
        boolean flag = contractWorkFlow.validateUpdateContractParameterLog();
        softAssert.assertTrue(flag, "Validated Updated Contract Parameters log");
    }

    @Then("validate Change Log details")
    public void validateChangeLogDetails() {
        boolean flag = contractWorkFlow.validateChangeLogComment();
        softAssert.assertTrue(flag, "Validated Change log View Data tab");
    }

    @And("user validate Contract parameter records")
    public void userValidateContractParameterRecords() {
        softAssert.assertTrue(new CommonPage().getTableRecordsCount() > 0,"Record Found Successfully");
    }

    @Then("user Set Parameter")
    public void userSetParameter() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_THREE_THOUSAND);
        new ContractPage().ClickOnSetParameters();
        new CommonPage().selectDropDown(FieldsEnum.REASON.getValue());
        new CommonPage().selectDropDownValue(GroupEnum.OTHER.getValue());
        new CommonPage().selectDropDown(FieldsEnum.NEW_VALUE.getValue());
        new CommonPage().selectDropDownValue(INBANKConstants.contractParameterNewValue1);
        new CommonPage().clickOnSaveBtn();
    }

    @And("user validate set parameter popup message")
    public void userValidateSetParameterPopupMessage() {
        softAssert.assertEquals(new CommonPage().getPopUpDialogText(), "Set Parameter: Successfully Completed","Successfully validated Set parameter PopUp message ");
    }

    @Then("user click on clear")
    public void userClickOnClear() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_THREE_THOUSAND);
        new CommonPage().lookUpWith(GlobalConstants.COLUMN_NAME,INBANKConstants.contractParameterRowName);
        new ContractPage().ClickOnClear();
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new CommonPage().selectDropDown(FieldsEnum.REASON.getValue());
        new CommonPage().selectDropDownValue(GroupEnum.OTHER.getValue());
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_THREE_THOUSAND);
        new CommonPage().clickOnSaveBtn();
    }

    @Then("user validate clear popup message")
    public void userValidateClearPopupMessage() {
        softAssert.assertEquals(new CommonPage().getPopUpDialogText(), "Clear: Successfully Completed", "Successfully validated clear PopUp message");
    }

    @Then("user validate Contract Classifiers records")
    public void userValidateContractClassifiersRecords() {
        new CommonPage().getTableRecordsCount();
        softAssert.assertTrue(new CommonPage().getTableRecordsCount() > 0,"Record Found Successfully");
    }

    @And("user Set Classifiers to Set Yes \\(BCC Is contract Overlimit)")
    public void userSetClassifiersToSetYesBCCIsContractOverlimit() {
        new CommonPage().lookUpWith(GlobalConstants.COLUMN_NAME, INBANKConstants.contractClassifiersName);
        new CommonPage().ClickSetClassifiersButton();
        new CommonPage().selectDropDown(FieldsEnum.REASON.getValue());
        new CommonPage().selectDropDownValue(GroupEnum.OTHER.getValue());
        new CommonPage().selectDropDown(FieldsEnum.TO_VALUE.getValue());
        new CommonPage().selectDropDownValue(GroupEnum.YES_NOK.getValue());
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new CommonPage().clickOnSaveBtn();
    }

    @And("user Set Classifiers to Set NO \\(BCC Is contract Overlimit)")
    public void userSetClassifiersToSetNOBCCIsContractOverlimit() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new CommonPage().ClickSetClassifiersButton();
        new CommonPage().selectDropDown(FieldsEnum.REASON.getValue());
        new CommonPage().selectDropDownValue(GroupEnum.OTHER.getValue());
        new CommonPage().selectDropDown(FieldsEnum.TO_VALUE.getValue());
        new CommonPage().selectDropDownValue(GroupEnum.NO_OK.getValue());
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new CommonPage().clickOnSaveBtn();
    }

    @Then("user validate Contract Classifier Change Log records")
    public void userValidateContractClassifierChangeLogRecords() {
        softAssert.assertTrue(new CommonPage().getTableRecordsCount() > 0,"Record Found Successfully");
    }

    @Then("user validate financial account details")
    public void userValidateFinancialAccountDetails() {
        new ContractWorkFlow().validateAccountsDetails();
    }

    @And("user validate Statement Entries details")
    public void userValidateStatementEntriesDetails() {
        new ContractWorkFlow().validateStatementEntriesDetails();
    }
}
