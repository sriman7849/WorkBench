package com.mastercard.gpse.processing.workflowsui;

import com.mastercard.gpse.processing.builders.CardBuilder;
import com.mastercard.gpse.processing.enums.*;
import com.mastercard.gpse.processing.managers.PageObjectManager;
import com.mastercard.gpse.processing.managers.Report;
import com.mastercard.gpse.processing.pages.*;
import com.mastercard.gpse.processing.utils.*;
import com.mastercard.gpse.processing.workflowsapi.WBPreRequisite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public class CardMainScreenWorkflow extends AbstractWorkflow {
    private static final Logger logger = (Logger) LogManager.getLogger(CommonPage.class);

    Map<String, String> reasonValues = new HashMap<>();

    CardBuilder cardBuilder;
    CardMainScreenPage cardMainScreenPage;
    SoftAssert softAssert = TestContext.softAssert;

    @FindBy(xpath = "//span[text()='Switch On' and @class='ng-binding']")
    private WebElement switchOn;


    public void validateCardMainScreenDetails() {
        PageObjectManager pageObjectFactory = new PageObjectManager();

        cardBuilder = (CardBuilder) WBPreRequisite.GlobalWBTestData.get(CardBuilder.class.getSimpleName());

        //SEGMENT
        String product = new CommonPage().getValueOf(GroupEnum.SEGMENT.getValue(), FieldsEnum.PRODUCT.getValue());
        softAssert.assertEquals(product, cardBuilder.getProductName(), "Product is displayed correctly");

        //GENERAL
        String contractName = new CommonPage().getValueOf(GroupEnum.GENERAL.getValue(), FieldsEnum.CONTRACT_NAME.getValue());
        String parentContract = new CommonPage().getValueOf(GroupEnum.GENERAL.getValue(), FieldsEnum.PARENT_CONTRACT.getValue());
        String billingContract = new CommonPage().getValueOf(GroupEnum.GENERAL.getValue(), FieldsEnum.BILLING_CONTRACT.getValue());
        String bankAccountNumber = new CommonPage().getValueOf(GroupEnum.GENERAL.getValue(), FieldsEnum.BANK_ACCOUNT_NUMBER.getValue());
        String openingDate = new CommonPage().getValueOf(GroupEnum.GENERAL.getValue(), FieldsEnum.OPENING_DATE.getValue());

        softAssert.assertEquals(contractName, cardBuilder.getCard().getContractName(), "Contract Name is displayed correctly");
        softAssert.assertEquals(parentContract, cardBuilder.getAccountContractNumber(), "Parent Contract is displayed correctly");
        softAssert.assertEquals(billingContract, cardBuilder.getAccountContractNumber(), "Billing Contract is displayed correctly");
        softAssert.assertEquals(bankAccountNumber, cardBuilder.getCardID(), "Bank Account Number is displayed correctly");
        softAssert.assertEquals(openingDate, DateUtility.changeDateFormat(cardBuilder.getDateOpen(), "yyyy-MM-dd", "dd/MM/yyyy"));

        //STATUS
        String status = new CommonPage().getValueOf(GroupEnum.STATUS.getValue(), FieldsEnum.STATUS.getValue());
        softAssert.assertEquals(status, cardBuilder.getCard().getCardStatus(), "Card Status is displayed correctly");

        //REFERENCE DATA
        String contractID = new CommonPage().getValueOf(GroupEnum.REFERENCE_DATA.getValue(), FieldsEnum.CONTRACT_ID.getValue());
        softAssert.assertEquals(contractID, cardBuilder.getCardID(), "Contract ID is displayed correctly");

        //CARD DATA
        String title = new CommonPage().getValueOf(GroupEnum.CARD_DATA.getValue(), FieldsEnum.TITLE.getValue());
        if (title.isEmpty()) {
            title = null;
        }
        String firstName = new CommonPage().getValueOf(GroupEnum.CARD_DATA.getValue(), FieldsEnum.FIRST_NAME.getValue());
        String lastName = new CommonPage().getValueOf(GroupEnum.CARD_DATA.getValue(), FieldsEnum.LAST_NAME.getValue());
        String companyName = new CommonPage().getValueOf(GroupEnum.CARD_DATA.getValue(), FieldsEnum.COMPANY_NAME.getValue());

        softAssert.assertEquals(title, cardBuilder.getCard().getEmbossedData().getEmbossedTitle(), "Title is displayed correctly");
        softAssert.assertEquals(firstName, cardBuilder.getCard().getEmbossedData().getEmbossedFirstName(), "First Name is displayed correctly");
        softAssert.assertEquals(lastName, cardBuilder.getCard().getEmbossedData().getEmbossedLastName(), "Last Name is displayed correctly");
        softAssert.assertEquals(companyName, cardBuilder.getCard().getEmbossedData().getEmbossedCompanyName(), "Company Name is displayed correctly");

    }

    /**
     * Validate the details, passed in pre req API calls, on Card Main Screen - Card Data Page
     */
    public void validateCardMainScreenCardDataDetails() {
        PageObjectManager pageObjectFactory = new PageObjectManager();

        cardBuilder = (CardBuilder) WBPreRequisite.GlobalWBTestData.get(CardBuilder.class.getSimpleName());

        //General
        String cardName = new CommonPage().getValueOf(TabTitleEnum.CARD_DATA.getValue(), GroupEnum.GENERAL.getValue(), FieldsEnum.CARD_NAME.getValue());
        String productCode = new CommonPage().getValueOf(TabTitleEnum.CARD_DATA.getValue(), GroupEnum.GENERAL.getValue(), FieldsEnum.PRODUCT.getValue());
        String cardExpiryDate = new CommonPage().getValueOf(TabTitleEnum.CARD_DATA.getValue(), GroupEnum.GENERAL.getValue(), FieldsEnum.EXPIRATION_DATE_YYMM.getValue());

        //REFERENCE DATA
        String parentContractName = new CommonPage().getValueOf(TabTitleEnum.CARD_DATA.getValue(), GroupEnum.REFERENCE_DATA.getValue(), FieldsEnum.PARENT.getValue());

        // Embossing Parms Data
        String embossTitle = new CommonPage().getValueOf(TabTitleEnum.CARD_DATA.getValue(), GroupEnum.EMBOSSING_PARMS.getValue(), FieldsEnum.TITLE.getValue());
        String embossFirstName = new CommonPage().getValueOf(TabTitleEnum.CARD_DATA.getValue(), GroupEnum.EMBOSSING_PARMS.getValue(), FieldsEnum.FIRST_NAME.getValue());
        String embossLastName = new CommonPage().getValueOf(TabTitleEnum.CARD_DATA.getValue(), GroupEnum.EMBOSSING_PARMS.getValue(), FieldsEnum.LAST_NAME.getValue());
        String embossCompanyName = new CommonPage().getValueOf(TabTitleEnum.CARD_DATA.getValue(), GroupEnum.EMBOSSING_PARMS.getValue(), FieldsEnum.COMPANY_NAME.getValue());

        softAssert.assertEquals(cardName, cardBuilder.getCard().getContractName(), "Validate Card Name successfully");
        productCode = productCode.replaceAll("INB ", "").replaceAll("\\s+", "_").replaceAll("-+", "_");
        productCode = productCode.toUpperCase();
        softAssert.assertEquals(productCode, cardBuilder.getCard().getProductCode(), "Validate Product Code successfully");
        softAssert.assertEquals(cardExpiryDate, DateUtility.changeDateFormat(cardBuilder.getCardExpiryDate(), "yymm", "yy-mm"), "Validate Card Expiry Date successfully");
        softAssert.assertEquals(parentContractName, cardBuilder.getAccountContractNumber(), "Validate Parent Contract successfully");
        if (embossTitle.isEmpty()) {
            embossTitle = null;
        }
        softAssert.assertEquals(embossTitle, cardBuilder.getCard().getEmbossedData().getEmbossedTitle(), "Validate Title successfully");
        softAssert.assertEquals(embossFirstName, cardBuilder.getCard().getEmbossedData().getEmbossedFirstName(), "Validate First Name successfully");
        softAssert.assertEquals(embossLastName, cardBuilder.getCard().getEmbossedData().getEmbossedLastName(), "Validate Last Name successfully");
        softAssert.assertEquals(embossCompanyName, cardBuilder.getCard().getEmbossedData().getEmbossedCompanyName(), "Validate Comapny Name successfully");
        Report.info(logger, "Validated Card Data Main- Card Data page");
    }

    public String isReasonCodeValuedisplayed() {
        return new CommonPage().getTitleOfTextArea(GroupEnum.GENERAL.getValue(), FieldsEnum.ADDITIONAL_INFO.getValue());
//            String reason = new CommonPage().getTitleOfTextArea(GroupEnum.GENERAL.getValue(), FieldsEnum.ADDITIONAL_INFO.getValue());
  //          Report.info(logger, "Reason: " + reason + "is displayed on Risk Controls details page");

      }
}
