package com.mastercard.gpse.processing.stepDefinitions.web;

import com.mastercard.gpse.processing.builders.AccountBuilder;
import com.mastercard.gpse.processing.builders.CardBuilder;
import com.mastercard.gpse.processing.builders.ClientBuilder;
import com.mastercard.gpse.processing.constants.BankConstants.INBANKConstants;
import com.mastercard.gpse.processing.constants.GlobalConstants;
import com.mastercard.gpse.processing.constants.MessageConstant;
import com.mastercard.gpse.processing.enums.FieldsEnum;
import com.mastercard.gpse.processing.enums.GroupEnum;
import com.mastercard.gpse.processing.enums.TabTitleEnum;
import com.mastercard.gpse.processing.managers.Report;
import com.mastercard.gpse.processing.pages.CardMainScreenPage;
import com.mastercard.gpse.processing.pages.CommonPage;
import com.mastercard.gpse.processing.pages.ContractsSearchPage;
import com.mastercard.gpse.processing.pages.FreeSearchPage;
import com.mastercard.gpse.processing.pages.navigation.Navigator;
import com.mastercard.gpse.processing.utils.*;
import com.mastercard.gpse.processing.workflowsapi.WBPreRequisite;
import com.mastercard.gpse.processing.workflowsui.CardMainScreenWorkflow;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CardMainScreenSteps {

    private static Logger logger = (Logger) LogManager.getLogger(CardMainScreenSteps.class);

    TestContext testContext;
    SoftAssert softAssert;

    CardBuilder cardBuilder;
    CardMainScreenPage cardMainScreenPage;
    AccountBuilder accountBuilder;
    CommonPage commonPage;
    ClientBuilder clientBuilder;

    public CardMainScreenSteps(TestContext testContext) {
        this.testContext = testContext;
        this.softAssert = TestContext.softAssert;
    }

    @Then("user click on CardNumber")
    public void userClickOnCardNumber() {
        cardBuilder = (CardBuilder) WBPreRequisite.GlobalWBTestData.get(CardBuilder.class.getSimpleName());
        String storingCardNumber = cardBuilder.getCardContractNumber();
        String lastFourDigitsOfCard = storingCardNumber.substring(storingCardNumber.length() - 4);
        new Navigator().navigateToWidgetMenuTab(lastFourDigitsOfCard);
        Report.info(logger, "User click on CardNumber");
    }
    @Then("user click on CardNumber {string}")
    public void userClickOnCardNumber(String lastFourDigitsOfCard) {
//        cardBuilder = (CardBuilder) WBPreRequisite.GlobalWBTestData.get(CardBuilder.class.getSimpleName());
//        String storingCardNumber = cardBuilder.getCardContractNumber();
        new Navigator().navigateToWidgetMenuTab(lastFourDigitsOfCard);
        Report.info(logger, "User click on CardNumber");
    }

    @Then("user click on card by last four digits of card number {string}")
    public void userClickOnCardByLastFourDigitsOfCardNumber(String lastFourDigitsOfCard) {
        new Navigator().navigateToWidgetMenuTab(lastFourDigitsOfCard);
        Report.info(logger, "User click on CardNumber");
    }

    @And("user validate CardMainScreen has {string}")
    public void userValidateCardMainScreenHas(String txt) {
        cardMainScreenPage = testContext.getPageObjectManager().getPage(CardMainScreenPage.class);
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        softAssert.assertEquals(txt, cardMainScreenPage.getcardDataText(), "Successfully validated ");
        Report.info(logger, "Validated CardMainScreen has " + txt);
    }

    @And("validate Full Data Fields")
    public void validateFullDataFields() {
        new CardMainScreenWorkflow().validateCardMainScreenDetails();
        Report.info(logger, "Validated CardMainScreen has Full Data Fields");
    }

    @And("user validate Billing History page records")
    public void userValidateBillingHistoryPageRecords() {
        new CommonPage().getTableRecordsCount();
        softAssert.assertTrue(new CommonPage().getTableRecordsCount() > 0, "Record Found Successfully");
    }

    @Then("click on the get clear pan")
    public void clickOnTheGetClearPan() {
        cardMainScreenPage = new CardMainScreenPage();
        cardMainScreenPage.navigateToGetClearPan();
        new CommonPage().clickOnOKBtn();
    }

    @And("validate Card number is displayed")
    public void validateCardNumberIsDisplayed() {
        cardBuilder = (CardBuilder) WBPreRequisite.GlobalWBTestData.get(CardBuilder.class.getSimpleName());
        cardMainScreenPage = new CardMainScreenPage();
        String cardNumberTxt = cardMainScreenPage.isgetClearPanAlertMessageDisplayed();
        String nameSubstring = CardMainScreenPage.hideMiddleDigits(cardNumberTxt);
        softAssert.assertEquals(cardBuilder.getCardContractNumber(), nameSubstring);

        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new CommonPage().clickOnOKBtn();
    }

    @Then("user search Card Details by Account Number")
    public void user_search_card_by_account_number() {
        cardBuilder = (CardBuilder) WBPreRequisite.GlobalWBTestData.get("CardBuilder");
        new FreeSearchPage().enterAccountNumber(cardBuilder.new Card().getAccountContractNumber());
        new CommonPage().clickFindBtn();
    }

    @Then("Validate the Financial Page")
    public void validateTheFinancialPage() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        cardBuilder = (CardBuilder) WBPreRequisite.GlobalWBTestData.get(CardBuilder.class.getSimpleName());
        String contractName = new CommonPage().getValueOf(TabTitleEnum.FINANCIALS.getValue(), GroupEnum.GENERAL.getValue(), FieldsEnum.CONTRACT_NAME.getValue());
        softAssert.assertEquals(contractName, cardBuilder.getCard().getContractName(), "Validate Contract Name successfully");
        Report.info(logger, "Validated Financial Page");
    }

    @And("Validate the Financial Details")
    public void validateTheFinancialDetails() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        cardBuilder = (CardBuilder) WBPreRequisite.GlobalWBTestData.get(CardBuilder.class.getSimpleName());
        String contractName = new CommonPage().getValueOf(TabTitleEnum.FINANCIAL_DETAILS
                .getValue(), GroupEnum.GENERAL.getValue(), FieldsEnum.CONTRACT_NAME.getValue());
        softAssert.assertEquals(contractName, cardBuilder.getCard().getContractName(), "Validate Contract Name successfully");
        Report.info(logger, "Validated Financial Details Page");
    }

    @Then("user click on Billing History of Financials")
    public void userClickOnBillingHistoryOfFinancials() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        cardMainScreenPage = new CardMainScreenPage();
        cardMainScreenPage = testContext.getPageObjectManager().getPage(CardMainScreenPage.class);
        cardMainScreenPage.clickOnBillingHistoryOfFinancials();
    }

    @And("Validate the Billing History Page of Financials")
    public void validateTheBillingHistoryPageOfFinancials() {
        commonPage = new CommonPage();
        commonPage.getTableRecordsCount();
        softAssert.assertTrue(commonPage.getTableRecordsCount() > 0, "Record Found Successfully");
        Report.info(logger, "Validated Billing History Page of Financials");

    }

    @Then("User set New status for the card Data")
    public void userSetNewStatusForTheCardData() {
        new CardMainScreenPage().setStatusForCardData("PickUp L 41");
        new CardMainScreenPage().selectCardDataNewStatusReason("Cardholder request");
        new CardMainScreenPage().enterCardDataSwitchOffComment(GlobalConstants.SET_STATUS_COMMENT);
        new CommonPage().clickOnSaveBtn();
        String msg = new CommonPage().getPopUpDialogText();
        softAssert.assertEquals(msg, MessageConstant.SET_STATUS_SUCCESS_MSG, "Success Message validated");
        Report.info(logger, "New Status saved successfully");

    }

    @Then("validate Card Instalments Data Field")
    public void validateCardInstalmentsDataField() {
        accountBuilder = (AccountBuilder) WBPreRequisite.GlobalWBTestData.get(AccountBuilder.class.getSimpleName());
        String totalDueAmount = new CommonPage().getValueOf(TabTitleEnum.INSTALMENTS.getValue(), GroupEnum.TOTALS.getValue(), FieldsEnum.TOTAL_DUE_AMOUNT.getValue());
        String amount = accountBuilder.getContractFinancials().getTotalDueAmount();
        NumberFormat formatter = new DecimalFormat("0.00");
        String formatedNumber = formatter.format(Double.parseDouble(amount));
        Report.info(logger, totalDueAmount + " " + totalDueAmount);
        softAssert.assertEquals(totalDueAmount, formatedNumber, "Total Amount validated");
    }

    @Then("user validate Card Classifiers records")
    public void userValidateCardClassifiersRecords() {
        new CommonPage().getTableRecordsCount();
        softAssert.assertTrue(new CommonPage().getTableRecordsCount() > 0, "Record Found Successfully");
    }

    @Then("User switch on the card data status")
    public void userSwitchOnTheCardDataStatus() {
        new CardMainScreenPage().selectCardDataSwitchOnReasonCode(GlobalConstants.REASONCODE);
        new CardMainScreenPage().enterCardDataSwitchOnComment(GlobalConstants.SWITCH_ON_COMMENT);
        new CommonPage().clickOnSaveBtn();
        String msg = new CommonPage().getPopUpDialogText();
        softAssert.assertEquals(msg, MessageConstant.SWITCH_ON_SUCCESS_MSG, "Success Message validated");
        Report.info(logger, " Switch On:Successfully Completed");
    }

    @Then("User switch off the card data status")
    public void userSwitchOffTheCardDataStatus() {
        new CardMainScreenPage().selectCardDataSwitchOffReasonCode(GlobalConstants.REASONCODE);
        new CardMainScreenPage().enterCardDataSwitchOffComment(GlobalConstants.SWITCH_OFF_COMMENT);
        new CommonPage().clickOnSaveBtn();
        String msg = new CommonPage().getPopUpDialogText();
        softAssert.assertEquals(msg, MessageConstant.SWITCH_OFF_SUCCESS_MSG, "Success Message validated");
        Report.info(logger, "Switch Off:Successfully Completed");
    }

    @Then("User Redefine the card data status")
    public void userRedefineTheCardDataStatus() {
        new CardMainScreenPage().selectCardDataRedefineReasonCode("Cardholder request");
        new CardMainScreenPage().enterCardDataRedefineComment(GlobalConstants.REDEFINE_COMMENT);
        new CommonPage().clickOnSaveBtn();
        String msg = new CommonPage().getPopUpDialogText();
        softAssert.assertEquals(msg, MessageConstant.REDEFINE_SUCCESS_MSG, "Success Message validated");
        Report.info(logger, "Redefine:Successfully Completed");
    }

    @Then("User Restore Original the card data status")
    public void userRestoreOriginalTheCardDataStatus() {
        new CardMainScreenPage().selectCardDataRestoreOriginalReasonCode("Cardholder request");
        new CardMainScreenPage().enterCardDataRestoreOriginalComment(GlobalConstants.RESTORE_ORIGINAL_COMMENT);
        new CommonPage().clickOnSaveBtn();
        String msg = new CommonPage().getPopUpDialogText();
        softAssert.assertEquals(msg, MessageConstant.RESTORE_ORIGINAL_SUCCESS_MSG, "Success Message validated");
        Report.info(logger, "Restore Original:Successfully Completed");
    }

    @Then("Validation on Card Data page")
    public void validation_on_card_data_page() {
        cardBuilder = (CardBuilder) WBPreRequisite.GlobalWBTestData.get(CardBuilder.class.getSimpleName());
        String cardName = new CommonPage().getValueOf(TabTitleEnum.CARD_DATA
                .getValue(), GroupEnum.GENERAL.getValue(), FieldsEnum.CARD_NAME.getValue());
        softAssert.assertEquals(cardName, cardBuilder.getCard().getContractName(), "Validate Card Name successfully");
        String firstName = new CommonPage().getValueOf(TabTitleEnum.CARD_DATA.getValue(), GroupEnum.EMBOSSING_PARMS.getValue(), FieldsEnum.FIRST_NAME.getValue());
        softAssert.assertEquals(firstName, cardBuilder.getCard().getEmbossedData().getEmbossedFirstName(), "Validate Embossed Params First Name  successfully");
        String lastName = new CommonPage().getValueOf(TabTitleEnum.CARD_DATA.getValue(), GroupEnum.EMBOSSING_PARMS.getValue(), FieldsEnum.LAST_NAME.getValue());
        softAssert.assertEquals(lastName, cardBuilder.getCard().getEmbossedData().getEmbossedLastName(), "Validate Embossed Params Last Name  successfully");
        String companyName = new CommonPage().getValueOf(TabTitleEnum.CARD_DATA.getValue(), GroupEnum.EMBOSSING_PARMS.getValue(), FieldsEnum.COMPANY_NAME.getValue());
        softAssert.assertEquals(companyName, cardBuilder.getCard().getEmbossedData().getEmbossedCompanyName(), "Validate Embossed Params Company Name  successfully");
        Report.info(logger, "Validated Card Data Main- Card Data page");

    }

    @And("validate Get clear pan button is not be visible")
    public void validateGetClearPanButtonIsNotBeVisible() {
        softAssert.assertFalse(new CardMainScreenPage().isGetClearPanButtonisdisplayed(), "validate Get clear pan button is not be visible");
    }

    @And("Validate the Risk Controls- Details Page")
    public void validateTheRiskControlsDetailsPage() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        cardBuilder = (CardBuilder) WBPreRequisite.GlobalWBTestData.get(CardBuilder.class.getSimpleName());
        String code = new CommonPage().getValueOf(TabTitleEnum.CARD_DATA
                .getValue(), GroupEnum.GENERAL.getValue(), FieldsEnum.CODE.getValue());
        softAssert.assertEquals(code, cardBuilder.getCard().getContractName(), "Validate Code successfully");
        Report.info(logger, "Validated Risk Controls - Details Page");
    }

    @Then("Validation Full Data on Card Data page")
    public void validationFullDataOnCardDataPage() {
        softAssert.assertTrue(new Navigator().isNavigationLinkAvailable("Card Data"), "Card Data Screen displayed.");
        new CardMainScreenWorkflow().validateCardMainScreenCardDataDetails();
    }


    @And("user click on Close Instalment Plans button")
    public void userClickOnCloseInstalmentPlansButton() {
        new CardMainScreenPage().ClickCloseInstalmentPlansButton();
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new CommonPage().selectDropDown(FieldsEnum.REASON.getValue());
        new CommonPage().selectDropDownValue(GroupEnum.OTHER.getValue());
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_THREE_THOUSAND);
        new CommonPage().clickOnSaveBtn();
    }

    @Then("user validate the Close Instalment plans popup message")
    public void userValidateTheCloseInstalmentPlansPopupMessage() {
        softAssert.assertEquals(new CommonPage().getPopUpDialogText(), "Close Instalment Plans: Successfully Completed", "Successfully validated Close Instalments Plans PopUp message");
    }

    @And("user Set Classifiers to Set No \\(ABU)")
    public void userSetClassifiersToSetNoABU() {
        new CommonPage().lookUpWith(GlobalConstants.COLUMN_NAME, INBANKConstants.cardClassifiersName);
        new CommonPage().ClickSetClassifiersButton();
        new CommonPage().selectDropDown(FieldsEnum.REASON.getValue());
        new CommonPage().selectDropDownValue(GroupEnum.OTHER.getValue());
        new CommonPage().selectDropDown(FieldsEnum.TO_VALUE.getValue());
        new CommonPage().selectDropDownValue(GroupEnum.NO.getValue());
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_THREE_THOUSAND);
        new CommonPage().clickOnSaveBtn();
    }

    @Then("validate risk controls details page")
    public void validateRiskControlsDetailsPage() {
        String reason = new CardMainScreenWorkflow().isReasonCodeValuedisplayed();
        softAssert.assertTrue(reason.contains(GlobalConstants.REASONCODE));
    }

    @Then("validate risk controls Switch Off One Day")
    public void validateRiskControlsSwitchOffOneDay() {
        new CardMainScreenPage().switchOffOneDay();
        new CardMainScreenPage().enterCardDataSwitchOnComment(GlobalConstants.SWITCH_ON_COMMENT);
        new CommonPage().clickOnSaveBtn();
        String msg = new CommonPage().getPopUpDialogText();
        softAssert.assertEquals(msg, MessageConstant.SWITCH_OFF_DAY_SUCCESS_MSG, "Success Message validated");
        Report.info(logger, "Switch Off 1 Day: Successfully Completed");
    }

    @And("user Set Classifiers to Set Yes\\(ABU)")
    public void userSetClassifiersToSetYesABU() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_THREE_THOUSAND);
        new CommonPage().ClickSetClassifiersButton();
        new CommonPage().selectDropDown(FieldsEnum.REASON.getValue());
        new CommonPage().selectDropDownValue(GroupEnum.OTHER.getValue());
        new CommonPage().selectDropDown(FieldsEnum.TO_VALUE.getValue());
        new CommonPage().selectDropDownValue(GroupEnum.YES.getValue());
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_THREE_THOUSAND);
        new CommonPage().clickOnSaveBtn();
    }

    @Then("user validate Card Classifier Change Log records")
    public void userValidateCardClassifierChangeLogRecords() {
        softAssert.assertTrue(new CommonPage().getTableRecordsCount() > 0, "Record Found Successfully");
    }


    @Then("user search cardDetails by card Number - {string}")
    public void userSearchCardDetailsByCardNumber(String cardNumber) {
        new CardMainScreenPage().enterCardNumber(cardNumber);
        new ContractsSearchPage().clickOnFind();
        Report.info(logger, "Searched card Details by card Number: " + cardNumber);
    }


}





