package com.mastercard.gpse.processing.stepDefinitions.web;

import com.mastercard.gpse.processing.constants.BankConstants.INBANKConstants;
import com.mastercard.gpse.processing.constants.GlobalConstants;
import com.mastercard.gpse.processing.enums.FieldsEnum;
import com.mastercard.gpse.processing.enums.GroupEnum;
import com.mastercard.gpse.processing.pages.ClientPage;
import com.mastercard.gpse.processing.pages.CommonPage;
import com.mastercard.gpse.processing.pages.navigation.Navigator;
import com.mastercard.gpse.processing.utils.SoftAssert;
import com.mastercard.gpse.processing.utils.TestContext;
import com.mastercard.gpse.processing.utils.WaitUtility;
import com.mastercard.gpse.processing.workflowsui.ClientWorkFlow;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class ClientSteps {

    private static Logger logger = (Logger) LogManager.getLogger(NavigationSteps.class);
    private TestContext testContext;
    private SoftAssert softAssert;

    CommonPage commonPage;

    public ClientSteps(TestContext context) {
        this.testContext = context;
        this.softAssert = TestContext.softAssert;
    }

    @And("user validate the client main screen full details")
    public void userValidateTheClientMainScreenDetails() {
        new ClientWorkFlow().validateClientMainScreenDetails();
    }

    @And("user validate the Memos Screen")
    public void userValidateTheClientMemoSScreen() {
        new ClientWorkFlow().validateClientMemosPage();
    }

    @And("user create new Memos")
    public void userCreateNewMemos() {
        new ClientWorkFlow().createNewMemos();
    }

    @And("user Validate the Memos")
    public void validateMemos() {
        new ClientWorkFlow().validateDetailsOnMemoPage();
    }

    @And("User Edit the created Memo")
    public void editTheNewlyCreatedMemos() {
        new ClientWorkFlow().userEditMemos();
    }

    @And("user Delete the newly created Memo")
    public void deleteTheCreatedMemo() {
        new ClientWorkFlow().userDeleteMemos();
    }

    @And("user Validate the Deleted Memos Status")
    public void validateDeletedMemosStatus() {
        new ClientWorkFlow().validateDeleteMemoStatus();
    }

    @Then("user validate Client Classifiers records")
    public void userValidateClientClassifiersRecords() {
        softAssert.assertTrue(new CommonPage().getTableRecordsCount() > 0,"Record Found Successfully");
    }

    @And("user set the GDPR Marketing Consents")
    public void userSetTheGDPRMarketingConsents() {
        new ClientWorkFlow().setTheGDPRMarketingConsents();
    }

    @And("user validate the GDPR screen details")
    public void userValidateTheGDPRScreenDetails() {
        new ClientWorkFlow().validateGDPRMarketingConsentsValues();
    }

    @And("user Validate the View Data Page")
    public void validateViewDataPage() {
        new ClientWorkFlow().validateViewDataPage();
    }

    @And("user validate debt details screen details")
    public void userValidateDebtDetailsScreenDetails() {
        new ClientWorkFlow().validateDebtDetailsPage();
    }

    @And("user Set Classifiers to Set Yes \\(GDPR Data Erasure Requested)")
    public void userSetClassifiersToSetYesGDPRDataErasureRequested() {
        new CommonPage().lookUpWith(GlobalConstants.COLUMN_NAME, INBANKConstants.clientClassifiersName);
        new CommonPage().ClickSetClassifiersButton();
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new CommonPage().selectDropDown(FieldsEnum.REASON.getValue());
        new CommonPage().selectDropDownValue(GroupEnum.OTHER.getValue());
        new CommonPage().selectDropDown(FieldsEnum.TO_VALUE.getValue());
        new CommonPage().selectDropDownValue(GroupEnum.YES.getValue());
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new CommonPage().clickOnSaveBtn();
    }

    @And("user Set Classifiers to Set No \\(GDPR Data Erasure Requested)")
    public void userSetClassifiersToSetNoGDPRDataErasureRequested() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new CommonPage().ClickSetClassifiersButton();
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new CommonPage().selectDropDown(FieldsEnum.REASON.getValue());
        new CommonPage().selectDropDownValue(GroupEnum.OTHER.getValue());
        new CommonPage().selectDropDown(FieldsEnum.TO_VALUE.getValue());
        new CommonPage().selectDropDownValue(GroupEnum.NO.getValue());
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new CommonPage().clickOnSaveBtn();
    }

    @Then("user validate Client Classifier Change Log records")
    public void userValidateClientClassifierChangeLogRecords() {
        softAssert.assertTrue(new CommonPage().getTableRecordsCount() > 0,"Record Found Successfully");
    }

    @And("user validate client Address details")
    public void userValidateAddressDetails() {
        new ClientWorkFlow().validateClientAddressesDetailsPage();
    }
}

