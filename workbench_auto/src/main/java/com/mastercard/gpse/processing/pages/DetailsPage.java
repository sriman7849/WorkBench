package com.mastercard.gpse.processing.pages;

import com.mastercard.gpse.processing.domain.customerserviceworkbench.Details;
import com.mastercard.gpse.processing.utils.WebElementUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DetailsPage extends BasePage {
    private static final Logger logger = (Logger) LogManager.getLogger(DetailsPage.class);

    public DetailsPage() {PageFactory.initElements(driver, this);}

    public DetailsPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//span[contains(text(),'Details') and @class='ng-binding']")
    private WebElement headerTitle;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[1]/div[2]/field-block[1]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtInstitution;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[1]/div[2]/field-block[3]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtClientType;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[1]/div[2]/field-block[1]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtInbank;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/field-block[1]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtFullName;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/field-block[2]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtShortName;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/field-block[6]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtFirstName;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/field-block[8]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtLastName;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/field-block[10]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtBirthPlace;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/field-block[11]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtBirthName;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/field-block[9]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement birthDate;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/field-block[16]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtTaxpayerId;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/field-block[17]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement dateExpire;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[3]/div[2]/field-block[1]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtHomePhone;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[3]/div[2]/field-block[2]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtMobilePhone;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[3]/div[2]/field-block[3]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtHomeFax;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[3]/div[2]/field-block[4]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtEmail;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[4]/div[2]/field-block[1]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtCountry;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[4]/div[2]/field-block[4]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtZipCode;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[4]/div[2]/field-block[2]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtState;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[4]/div[2]/field-block[3]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtCity;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[4]/div[2]/field-block[5]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtAddressLine1;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[5]/div[2]/field-block[1]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtCompanyName;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[5]/div[2]/field-block[2]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtTrademark;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[5]/div[2]/field-block[3]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtDepartment;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[5]/div[2]/field-block[4]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtPosition;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[5]/div[2]/field-block[10]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtBusinessPhone;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[6]/div[2]/field-block[1]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtEmbossingTitle;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[6]/div[2]/field-block[2]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtEmbossingLastName;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[6]/div[2]/field-block[3]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtEmbossingFirstName;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[6]/div[2]/field-block[4]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtEmbossingCompanyName;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[7]/div[2]/field-block[1]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtIdentityCardType;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[7]/div[2]/field-block[2]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtIdentityCardNumber;
    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[7]/div[2]/field-block[3]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtClientNumber;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[7]/div[2]/field-block[4]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtSecretPhrase;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[7]/div[2]/field-block[7]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtSocialSecurityNumber ;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[8]/div[2]/field-block[1]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement registrationDate;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[8]/div[2]/field-block[7]/div/div[2]/field/readonly-field/input")
    private WebElement txtReady;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[8]/div[2]/field-block[6]/div/div[2]/field/readonly-field/input")
    private WebElement txtLastActionStatus;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[4]/div[1]/div[4]/icon[1]/span[1]/*[1]")
    private WebElement closeButton;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/field-block[7]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtMiddleName;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/field-block[5]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtGender;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/field-block[13]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtCitizenship;

    @FindBy(xpath = "//body/div[2]/layout-manager[1]/div[1]/layout[1]/div[1]/div[6]/tab[1]/div[1]/div[6]/div[3]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/field-block[15]/div[1]/div[2]/field[1]/readonly-field[1]/input[1]")
    private WebElement txtTaxerBracket;

    @FindBy(xpath="//div[@class='tab-element breadcrumbs-actions tab-bottom breadcrumb-container ng-scope']//span[2]/span[contains(text(),'Accounts')]")
    private WebElement accountsPageLink;

    @FindBy(xpath="//div[@class='tab-element breadcrumbs-actions tab-bottom breadcrumb-container ng-scope']//span[3]/span[contains(text(),'Transactions')]")
    private WebElement transactionsPageLink;

    @FindBy(xpath="//div[@class='tab-element breadcrumbs-actions tab-bottom breadcrumb-container ng-scope']//span[3]/span[contains(text(),'Cards')]")
    private WebElement cardsPageLink;

    @FindBy(xpath="//div[@class='tab-element breadcrumbs-actions tab-bottom breadcrumb-container ng-scope']//div[4]/span[3]/span[contains(text(),'Addresses')]")
    private WebElement addressesPageLink;

    @FindBy(xpath="//div[@class='tab-element breadcrumbs-actions tab-bottom breadcrumb-container ng-scope']//span[3]/span[contains(text(),'Own Addresses')]")
    private WebElement ownAddressesPageLink;

    @FindBy(xpath="//div[@class='tab-element breadcrumbs-actions tab-bottom breadcrumb-container ng-scope']//span[3]/span[contains(text(),'Classifiers')]")
    private WebElement classifiersPageLink;

    @FindBy(xpath="//div[@class='tab-element breadcrumbs-actions tab-bottom breadcrumb-container ng-scope']//span[3]/span[contains(text(),'Parameters')]")
    private WebElement parametersPageLink;

    @FindBy(xpath="//div[@class='tab-element breadcrumbs-actions tab-bottom breadcrumb-container ng-scope']//span[3]/span[contains(text(),'Notifications')]")
    private WebElement notificationsPageLink;

    @FindBy(xpath="//div[@class='tab-element breadcrumbs-actions tab-bottom breadcrumb-container ng-scope']//span[3]/span[contains(text(),'Cases')]")
    private WebElement casesPageLink;

    @FindBy(xpath="//div[@class='tab-element breadcrumbs-actions tab-bottom breadcrumb-container ng-scope']//span[3]/span[contains(text(),'Application Info')]")
    private WebElement applicationInfoPageLink;

    //@FindBy(xpath="//div[@class='tab-element breadcrumbs-actions tab-bottom breadcrumb-container ng-scope']//div/div[13]")
    @FindBy(xpath="//span[text()='Additional Info' and @class='link-span ng-binding']")
    private WebElement additionalInfoPageLink;

    @FindBy(xpath="//div[@class='tab-element breadcrumbs-actions tab-bottom breadcrumb-container ng-scope']//div/div[14]")
    private WebElement historyPageLink;

    @FindBy(xpath="//div[@class='tab-element breadcrumbs-actions tab-bottom breadcrumb-container ng-scope']//div/div[15]")
    private WebElement changedLogPageLink;

    @FindBy(xpath="//div[@class='tab-element breadcrumbs-actions tab-bottom breadcrumb-container ng-scope']//span[3]/span[contains(text(),'Linked Clients')]")
    private WebElement linkedClientsPageLink;

    @FindBy(xpath="//div[@class='tab-element breadcrumbs-actions tab-bottom breadcrumb-container ng-scope']//span[3]/span[contains(text(),'Payees')]")
    private WebElement payeesPageLink;

    //@FindBy(xpath="//div[@class='tab-element breadcrumbs-actions tab-bottom breadcrumb-container ng-scope']/div/div/ng-transclude/div[17]/span")
    @FindBy(xpath="//div[@class='br-step step-more tab-label tab-label-more br-menu-button link-more ng-scope']/span[contains(text(),'more')]")
    private WebElement more;


    boolean flag;
    public boolean validateClientDetails(Details dtls){
        flag = true;
        logger.info("-------Validating Client Details ------------");
        verifyOnScreenFieldValue(dtls.getInstitution(),txtInstitution,"Institution");
       // verifyOnScreenFieldValue(dtls.getClientType(),txtClientType, "ClientType");
        verifyOnScreenFieldValue(dtls.getFullName(),txtFullName,"FullName");
        verifyOnScreenFieldValue(dtls.getShortName(),txtShortName, "ShortName");
        verifyOnScreenFieldValue(dtls.getFirstName(),txtFirstName, "FirstName");
        verifyOnScreenFieldValue(dtls.getLastName(),txtLastName, "LastName");
        verifyOnScreenFieldValue(dtls.getMiddleName(),txtMiddleName, "MiddleName");
        verifyOnScreenFieldValue(dtls.getGender(),txtGender, "Gender");
        verifyOnScreenFieldValue(dtls.getBirthDate(),birthDate, "BirthDate");
        verifyOnScreenFieldValue(dtls.getBirthName(),txtBirthName, "BirthName");
        verifyOnScreenFieldValue(dtls.getBirthPlace(),txtBirthPlace, "BirthPlace");
        verifyOnScreenFieldValue(dtls.getCitizenship(),txtCitizenship, "Citizenship");
        verifyOnScreenFieldValue(dtls.getTaxpayerID(),txtTaxpayerId, "TaxpayerId");
        verifyOnScreenFieldValue(dtls.getTaxBracket(),txtTaxerBracket, "TaxerBracket");
        verifyOnScreenFieldValue(dtls.getDateExpire(),dateExpire, "DateExpire");
        verifyOnScreenFieldValue(dtls.getHomePhone(),txtHomePhone, "HomePhone");
        verifyOnScreenFieldValue(dtls.getHomeFax(),txtHomeFax, "HomeFax");

        verifyOnScreenFieldValue(dtls.getCountry(),txtCountry, "Country");
        verifyOnScreenFieldValue(dtls.getState(),txtState, "State");
        verifyOnScreenFieldValue(dtls.getCity(),txtCity, "City");
        verifyOnScreenFieldValue(dtls.getAddressLine1(),txtAddressLine1, "AddressLine1");

        verifyOnScreenFieldValue(dtls.getTrademark(),txtTrademark, "Trademark");
        verifyOnScreenFieldValue(dtls.getCompanyName(),txtCompanyName, "CompanyName");
        verifyOnScreenFieldValue(dtls.getDepartment(),txtDepartment, "Department");
        verifyOnScreenFieldValue(dtls.getPosition(),txtPosition, "Position");

        verifyOnScreenFieldValue(dtls.getEmbossCompanyName(),txtEmbossingCompanyName, "EmbossCompanyName");
        verifyOnScreenFieldValue(dtls.getEmbossfirstName(),txtEmbossingFirstName, "EmbossfirstName");
        verifyOnScreenFieldValue(dtls.getEmbosslastName(),txtEmbossingLastName, "EmbosslastName");

        verifyOnScreenFieldValue(dtls.getIdentityCardNumber(),txtIdentityCardNumber, "IdentityCardNumber");
        verifyOnScreenFieldValue(dtls.getIdentityCardType(),txtIdentityCardType, "IdentityCardType");
        verifyOnScreenFieldValue(dtls.getClientNumber(),txtClientNumber, "ClientNumber");
        verifyOnScreenFieldValue(dtls.getSecretPhrase(),txtSecretPhrase, "SecretPhrase");
        verifyOnScreenFieldValue(dtls.getSocialSecurityNumber(),txtSocialSecurityNumber, "SocialSecurityNumber");

        verifyOnScreenFieldValue(dtls.getRegistrationDate(),registrationDate, "RegistrationDate");
        verifyOnScreenFieldValue(dtls.getReady(),txtReady, "Ready");
        verifyOnScreenFieldValue(dtls.getLastActionStatus(),txtLastActionStatus, "LastActionStatus");
        logger.info("-------Client Details Validated------------");
           return flag;

    }

    public void verifyOnScreenFieldValue(String expectedValue, WebElement element,String field){

        String expected = expectedValue.trim();
        String actual = new WebElementUtility().getElementTitleAttributeValue(element);

        if(!expected.equals("nodata")) {
            if(expected.equalsIgnoreCase(actual)){
                String msg = "Expected Value: "+expected + " = Actual Value: "+actual + ", [Field Name - "+field+"]";
                logger.info(msg);
            }
            else{
                String msg = "Expected Value: "+expected + " != Actual Value: "+actual + ", [Field Name - "+field+"]";;
                logger.error(msg);
                flag = false;
            }
        }
    }

    public String getFullName(){
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(txtFullName);
        return txtFullName.getAttribute("title");

    }

    public String getShortName(){
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(txtShortName);
        return txtShortName.getAttribute("title");

    }

    public String getSource(){
        return driver.getPageSource();
    }

/*
    public ClientsPage closeDetailsPage(){
        closeButton.click();
        return new ClientsPage();
    }

    public ContractPage closeContractDetailsPage(){
        closeButton.click();
        return new ContractPage();
    }
*/

    public void clickAccountsPageLink()
    {
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(accountsPageLink);
        accountsPageLink.click();
        
    }

    public void clickTransactionsPageLink()
    {
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(transactionsPageLink);
        transactionsPageLink.click();
        
    }

    public void clickCardsPageLink()
    {
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(cardsPageLink);
        cardsPageLink.click();
        
    }

    public void clickAddressesPageLink()
    {
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(addressesPageLink);
        addressesPageLink.click();
        
    }

    public void clickOwnAddressesPageLink()
    {
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(ownAddressesPageLink);
        ownAddressesPageLink.click();
        
    }

    public void clickClassifiersPageLink()
    {
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(classifiersPageLink);
        classifiersPageLink.click();
        
    }

    public void clickParametersPageLink()
    {
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(parametersPageLink);
        parametersPageLink.click();
        
    }

    public void clickNotificationsPageLink()
    {
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(notificationsPageLink);
        notificationsPageLink.click();
        
    }

    public void clickCasesPageLink()
    {
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(casesPageLink);
        casesPageLink.click();
        
    }

    public void clickApplicationInfoPageLink()
    {
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(applicationInfoPageLink);
        applicationInfoPageLink.click();
        
    }

    public void clickAdditionalInfoPageLink()
    {
        clickMoreLink();
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(additionalInfoPageLink);
        additionalInfoPageLink.click();
    }

    public void clickHistoryPageLink()
    {
        clickMoreLink();
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(historyPageLink);
        historyPageLink.click();
    }

    public void clickChangeLogPageLink()
    {
        clickMoreLink();
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(changedLogPageLink);
        changedLogPageLink.click();
    }

    public void clickLinkedClientsPageLink()
    {
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(linkedClientsPageLink);
        linkedClientsPageLink.click();
        
    }

    public void clickPayeesPageLink()
    {
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(payeesPageLink);
        payeesPageLink.click();
    }

    public void clickMoreLink(){
        try{
            new WebElementUtility().waitForElementToBeClickableAfterPageLoading(more);
            more.click();
        }
        catch (Exception e){
            logger.error("More Link is not clickable: "+e.getMessage());

        }
    }
}

