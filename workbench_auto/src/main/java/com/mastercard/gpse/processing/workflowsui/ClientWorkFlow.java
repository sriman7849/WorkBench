package com.mastercard.gpse.processing.workflowsui;

import com.mastercard.gpse.processing.builders.AccountBuilder;
import com.mastercard.gpse.processing.builders.ClientBuilder;
import com.mastercard.gpse.processing.constants.GlobalConstants;
import com.mastercard.gpse.processing.enums.*;
import com.mastercard.gpse.processing.pages.ClientPage;
import com.mastercard.gpse.processing.pages.CommonPage;
import com.mastercard.gpse.processing.utils.*;
import com.mastercard.gpse.processing.workflowsapi.WBPreRequisite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.Map;


public class ClientWorkFlow extends AbstractWorkflow {
    private static final Logger logger = (Logger) LogManager.getLogger(ClientWorkFlow.class);

    ClientBuilder clientBuilder=new ClientBuilder();
    AccountBuilder accountBuilder = new AccountBuilder();
    SoftAssert softAssert = TestContext.softAssert;
    ClientPage clientPage;
    CommonPage commonPage = new CommonPage();

    /**
     * Validate the details, passed in pre req API calls, on Client Main Screen Page
     */
    public void validateClientMainScreenDetails() {
        clientBuilder = (ClientBuilder) WBPreRequisite.GlobalWBTestData.get(ClientBuilder.class.getSimpleName());

        CommonPage clientPage = pageObjectFactory.getPage(CommonPage.class);

        // Personal Data
        String fullName = clientBuilder.getClientPersonalData().getFirstName() + " " + clientBuilder.getClientPersonalData().getMiddleName() + " " + clientBuilder.getClientPersonalData().getLastName();
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.PERSONAL_DATA.getValue(), FieldsEnum.FULL_NAME.getValue()), fullName, "Full name is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.PERSONAL_DATA.getValue(), FieldsEnum.SHORT_NAME.getValue()), clientBuilder.getClientPersonalData().getShortName(), "Short Name is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.PERSONAL_DATA.getValue(), FieldsEnum.GENDER.getValue()), GenderEnum.valueOf(clientBuilder.getClientPersonalData().getGender()).getValue(), "Gender is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.PERSONAL_DATA.getValue(), FieldsEnum.FIRST_NAME.getValue()), clientBuilder.getClientPersonalData().getFirstName(), "First Name is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.PERSONAL_DATA.getValue(), FieldsEnum.MIDDLE_NAME.getValue()), clientBuilder.getClientPersonalData().getMiddleName(), "Middle Name is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.PERSONAL_DATA.getValue(), FieldsEnum.LAST_NAME.getValue()), clientBuilder.getClientPersonalData().getLastName(), "Last Name is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.PERSONAL_DATA.getValue(), FieldsEnum.BIRTH_DATE.getValue()), DateUtility.changeDateFormat(clientBuilder.getClientPersonalData().getBirthDate(), "yyyy-MM-dd", "dd/MM/yyyy"), "Birth Date is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.PERSONAL_DATA.getValue(), FieldsEnum.BIRTH_PLACE.getValue()), clientBuilder.getClientPersonalData().getBirthPlace(), "Birth Place is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.PERSONAL_DATA.getValue(), FieldsEnum.BIRTH_NAME.getValue()), clientBuilder.getClientPersonalData().getBirthName(), "Birth Name is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.PERSONAL_DATA.getValue(), FieldsEnum.CITIZENSHIP.getValue()), CountryEnum.valueOf(clientBuilder.getClientPersonalData().getCitizenship()).getValue(), "Citizenship is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.PERSONAL_DATA.getValue(), FieldsEnum.TAX_BRACKET.getValue()), clientBuilder.getClientIdentificationData().getTaxPosition(), "Tax Bracket is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.PERSONAL_DATA.getValue(), FieldsEnum.TAXPAYER_ID.getValue()), clientBuilder.getClientIdentificationData().getTaxpayerIdentifier(), "Taxpayer ID is displayed correctly");

        // Address
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.ADDRESS.getValue(), FieldsEnum.STATE.getValue()), clientBuilder.getClientBaseAddressData().getState(), "State is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.ADDRESS.getValue(), FieldsEnum.CITY.getValue()), clientBuilder.getClientBaseAddressData().getCity(), "City is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.ADDRESS.getValue(), FieldsEnum.ZIP_CODE.getValue()), clientBuilder.getClientBaseAddressData().getPostalCode(), "ZIP Code is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.ADDRESS.getValue(), FieldsEnum.ADDRESS_LINE_1.getValue()), clientBuilder.getClientBaseAddressData().getAddressLine1(), "Address Line 1 is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.ADDRESS.getValue(), FieldsEnum.ADDRESS_LINE_2.getValue()), clientBuilder.getClientBaseAddressData().getAddressLine2(), "Address Line 2 is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.ADDRESS.getValue(), FieldsEnum.ADDRESS_LINE_3.getValue()), clientBuilder.getClientBaseAddressData().getAddressLine3(), "Address Line 3 is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.ADDRESS.getValue(), FieldsEnum.ADDRESS_LINE_4.getValue()), clientBuilder.getClientBaseAddressData().getAddressLine4(), "Address Line 4 is displayed correctly");

        // Company Data
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.COMPANY_DATA.getValue(), FieldsEnum.COMPANY_NAME.getValue()), clientBuilder.getClientCompanyData().getCompanyName(), "Company Name is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.COMPANY_DATA.getValue(), FieldsEnum.TRADEMARK.getValue()), clientBuilder.getClientCompanyData().getCompanyTradeName(), "Trademark is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.COMPANY_DATA.getValue(), FieldsEnum.DEPARTMENT.getValue()), clientBuilder.getClientCompanyData().getCompanyDepartment(), "Department is displayed correctly");

        // Embossing Params
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.EMBOSSING_PARMS.getValue(), FieldsEnum.LAST_NAME.getValue()), clientBuilder.getEmbossedData().getEmbossedLastName(), "Last Name is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.EMBOSSING_PARMS.getValue(), FieldsEnum.FIRST_NAME.getValue()), clientBuilder.getEmbossedData().getEmbossedFirstName(), "First Name is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.COMPANY_DATA.getValue(), FieldsEnum.COMPANY_NAME.getValue()), clientBuilder.getClientCompanyData().getCompanyName(), "Company Name is displayed correctly");

        // Identification Data
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.IDENTIFICATION_DATA.getValue(), FieldsEnum.IDENTITY_CARD_TYPE.getValue()), clientBuilder.getClientIdentificationData().getIdentificationDocumentType(), "Identity Card Type is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.IDENTIFICATION_DATA.getValue(), FieldsEnum.IDENTITY_CARD_NUMBER.getValue()), clientBuilder.getClientIdentificationData().getIdentificationDocumentNumber(), "Identity Card Number is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.IDENTIFICATION_DATA.getValue(), FieldsEnum.CLIENT_NUMBER.getValue()), clientBuilder.getClientNumber(), "Client Number is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.IDENTIFICATION_DATA.getValue(), FieldsEnum.SECRET_PHRASE.getValue()), clientBuilder.getClientPersonalData().getSecretPhrase(), "Secret Phrase is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(GroupEnum.IDENTIFICATION_DATA.getValue(), FieldsEnum.SOCIAL_SECURITY_NUMBER.getValue()), clientBuilder.getClientIdentificationData().getSocialNumber(), "Social Security Number is displayed correctly");
    }

    /**
     * Validate Memos page on client Main Screen
     */
    public void validateClientMemosPage() {
        softAssert.assertTrue(new ClientPage().isMemoTypeAvailable(), "Memos Type Title is present");
    }

    /**
     * Create a new Memos
     */
    public void createNewMemos() {
        clientPage = new ClientPage();
        commonPage.clickOnCreateMemos();
        commonPage.selectMemosReason();
        commonPage.selectMemoType();
        String message = commonPage.enterOriginalMessage(RandomUtils.getRandomAlphabeticString(5));
        new CommonPage().clickOnSaveBtn();
        clientBuilder.setOriginalMessage(message);
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_FIVE_THOUSAND);

    }

    /***
     * user Edit Memos
     */
    public void userEditMemos() {
        clientPage = new ClientPage();
        if (!new ClientPage().availabilityOfDetailsLink()) {
            createNewMemos();
        }
        commonPage.editMemos();
        commonPage.selectMemosReason();
        String message = commonPage.enterOriginalMessage(RandomUtils.getRandomAlphabeticString(5));
        new CommonPage().clickOnSaveBtn();
        clientBuilder.setOriginalMessage(message);
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_FIVE_THOUSAND);
    }

    /**
     * user Delete Memos
     */
    public void userDeleteMemos() {
        clientPage = new ClientPage();
        if (!new ClientPage().availabilityOfDetailsLink()) {
            createNewMemos();
        }
        commonPage.deleteMemos();
        new CommonPage().clickOnOKBtn();
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_ONE_THOUSAND);
        commonPage.selectMemosReason();
        new CommonPage().clickOnSaveBtn();
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_FIVE_THOUSAND);
    }

    /**
     * Validate the Details on Memo Page
     */
    public void validateDetailsOnMemoPage() {
        softAssert.assertEquals(new ClientPage().getMessageFromMemoPage(), clientBuilder.getOriginalMessage(), "Original Message is displayed as expected");
    }

    /**
     * Validate Deleted Memos Status
     */
    public void validateDeleteMemoStatus() {
        softAssert.assertTrue(new ClientPage().getMemosTableStatus(), "Record is found");
    }

    /**
     *
     * Set the GDPR Marketing Consents on GDPR page in client main screen
     */
    public void setTheGDPRMarketingConsents() {
        ClientPage gdprPage = pageObjectFactory.getPage(ClientPage.class);
        gdprPage.clickOnSetGDPRMarketingConsentsButton();
        new DropDown().selectDropDownByVisibleText(FieldsEnum.SFS1_PROFILING_DD.getValue(), GlobalConstants.SFS1_PROFILING);
        new DropDown().selectDropDownByVisibleText(FieldsEnum.SFS2_PARTNERS_OFFERS_DD.getValue(), GlobalConstants.SFS2_PARTNERS_OFFERS);
        new DropDown().selectDropDownByVisibleText(FieldsEnum.SFS3_TRANSFER_DATA_DD.getValue(), GlobalConstants.SFS3_TRANSFER_DATA);
        new DropDown().selectDropDownByVisibleText(FieldsEnum.BNP1_PROFILING_DD.getValue(), GlobalConstants.BNP1_PROFILING);
        new DropDown().selectDropDownByVisibleText(FieldsEnum.BNP2_PARTNERS_OFFERS_DD.getValue(), GlobalConstants.BNP2_PARTNERS_OFFERS);
        new CommonPage().clickOnSaveBtn();
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
    }

    /**
     * Validate the set GDPR Marketing Consents values on GDPR page in client main screen
     */
    public void validateGDPRMarketingConsentsValues() {
        softAssert.assertEquals(new CommonPage().getValueOf(TabTitleEnum.GDPR.getValue(), GroupEnum.GDPR_MARKETING_CONSENTS.getValue(), FieldsEnum.SFS1_PROFILING.getValue()), GlobalConstants.SFS1_PROFILING, "(SFS1) Profiling passed");
        softAssert.assertEquals(new CommonPage().getValueOf(TabTitleEnum.GDPR.getValue(), GroupEnum.GDPR_MARKETING_CONSENTS.getValue(), FieldsEnum.SFS2_PARTNERS_OFFERS.getValue()), GlobalConstants.SFS2_PARTNERS_OFFERS, "(SFS2) Partners Offers passed");
        softAssert.assertEquals(new CommonPage().getValueOf(TabTitleEnum.GDPR.getValue(), GroupEnum.GDPR_MARKETING_CONSENTS.getValue(), FieldsEnum.SFS3_TRANSFER_DATA.getValue()), GlobalConstants.SFS3_TRANSFER_DATA, "(SFS3) Transfer Data passed");
        softAssert.assertEquals(new CommonPage().getValueOf(TabTitleEnum.GDPR.getValue(), GroupEnum.GDPR_MARKETING_CONSENTS.getValue(), FieldsEnum.BNP1_PROFILING.getValue()), GlobalConstants.BNP1_PROFILING, "(BNP1) Profiling passed");
        softAssert.assertEquals(new CommonPage().getValueOf(TabTitleEnum.GDPR.getValue(), GroupEnum.GDPR_MARKETING_CONSENTS.getValue(), FieldsEnum.BNP2_PARTNERS_OFFERS.getValue()), GlobalConstants.BNP2_PARTNERS_OFFERS, "(BNP2) Partners Offers passed");
    }

    /**
     * Validate the Full Name, passed in pre req API calls, on Client Main Screen View Data Page
     */
    public void validateViewDataPage() {
        clientBuilder = (ClientBuilder) WBPreRequisite.GlobalWBTestData.get(ClientBuilder.class.getSimpleName());
        CommonPage clientPage = pageObjectFactory.getPage(CommonPage.class);
        // General
        String fullName = clientBuilder.getClientPersonalData().getFirstName() + " " + clientBuilder.getClientPersonalData().getMiddleName() + " " + clientBuilder.getClientPersonalData().getLastName();
        softAssert.assertEquals(clientPage.getValueOf("General", "Application Object"), "Client:" + " " + fullName, "Full name is displayed correctly");
    }

    /**
     * Validate the details on Client Main Screen - Debt - Details page
     */
    public void validateDebtDetailsPage() {
        accountBuilder = (AccountBuilder) WBPreRequisite.GlobalWBTestData.get(AccountBuilder.class.getSimpleName());
        new WaitUtility().waitForPageToLoad();
        softAssert.assertEquals(new CommonPage().getValueOf(TabTitleEnum.DETAILS.getValue(), GroupEnum.GENERAL.getValue(), FieldsEnum.CONTRACT_NUMBER.getValue()), accountBuilder.getAccount().getAccountContractNumber(), "Contract Number is displayed correctly");
        softAssert.assertEquals(new CommonPage().getValueOf(TabTitleEnum.DETAILS.getValue(), GroupEnum.GENERAL.getValue(), FieldsEnum.CONTRACT_NAME.getValue()), accountBuilder.getAccount().getContractName(), "Contract Name is displayed correctly");
    }

    /**
     * Validate the details on Client Main Screen - Addresses - Details page
     */
    public void validateClientAddressesDetailsPage() {
        clientBuilder = (ClientBuilder) WBPreRequisite.GlobalWBTestData.get(ClientBuilder.class.getSimpleName());
        CommonPage clientPage = pageObjectFactory.getPage(CommonPage.class);

        // Postal Address
        softAssert.assertEquals(clientPage.getValueOf(TabTitleEnum.DETAILS.getValue(), GroupEnum.POSTAL_ADDRESS.getValue(), FieldsEnum.COUNTRY.getValue()), CountryEnum.valueOf(clientBuilder.getClientPersonalData().getCountryCode()).getValue(), "Country is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(TabTitleEnum.DETAILS.getValue(), GroupEnum.POSTAL_ADDRESS.getValue(), FieldsEnum.STATE.getValue()), clientBuilder.getClientBaseAddressData().getState(), "State is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(TabTitleEnum.DETAILS.getValue(), GroupEnum.POSTAL_ADDRESS.getValue(), FieldsEnum.CITY.getValue()), clientBuilder.getClientBaseAddressData().getCity(), "City is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(TabTitleEnum.DETAILS.getValue(), GroupEnum.POSTAL_ADDRESS.getValue(), FieldsEnum.ZIP_CODE.getValue()), clientBuilder.getClientBaseAddressData().getPostalCode(), "ZIP Code is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(TabTitleEnum.DETAILS.getValue(), GroupEnum.POSTAL_ADDRESS.getValue(), FieldsEnum.ADDRESS_LINE_1.getValue()), clientBuilder.getClientBaseAddressData().getAddressLine1(), "Address Line 1 is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(TabTitleEnum.DETAILS.getValue(), GroupEnum.POSTAL_ADDRESS.getValue(), FieldsEnum.ADDRESS_LINE_2.getValue()), clientBuilder.getClientBaseAddressData().getAddressLine2(), "Address Line 2 is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(TabTitleEnum.DETAILS.getValue(), GroupEnum.POSTAL_ADDRESS.getValue(), FieldsEnum.ADDRESS_LINE_3.getValue()), clientBuilder.getClientBaseAddressData().getAddressLine3(), "Address Line 3 is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(TabTitleEnum.DETAILS.getValue(), GroupEnum.POSTAL_ADDRESS.getValue(), FieldsEnum.ADDRESS_LINE_4.getValue()), clientBuilder.getClientBaseAddressData().getAddressLine4(), "Address Line 4 is displayed correctly");

        // Personal Data
        softAssert.assertEquals(clientPage.getValueOf(ScreenCodeEnum.CLIENT_ADDRESS_DETAILS.getValue(), TabTitleEnum.DETAILS.getValue(), GroupEnum.PERSONAL_DATA.getValue(), FieldsEnum.FIRST_NAME.getValue()), clientBuilder.getClientPersonalData().getFirstName(), "First Name is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(ScreenCodeEnum.CLIENT_ADDRESS_DETAILS.getValue(), TabTitleEnum.DETAILS.getValue(), GroupEnum.PERSONAL_DATA.getValue(), FieldsEnum.BIRTH_NAME.getValue()), clientBuilder.getClientPersonalData().getBirthName(), "Birth Name is displayed correctly");
        softAssert.assertEquals(clientPage.getValueOf(ScreenCodeEnum.CLIENT_ADDRESS_DETAILS.getValue(), TabTitleEnum.DETAILS.getValue(), GroupEnum.PERSONAL_DATA.getValue(), FieldsEnum.LAST_NAME.getValue()), clientBuilder.getClientPersonalData().getLastName(), "Last Name is displayed correctly");
    }
}