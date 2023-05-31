package com.mastercard.gpse.processing.pages;
import com.mastercard.gpse.processing.pages.navigation.CustomerServiceWorkbenchNav;
import com.mastercard.gpse.processing.pages.navigation.Navigation;
import com.mastercard.gpse.processing.utils.WebElementUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Navigation(tabTitle = CustomerServiceWorkbenchNav.TAB_TITLE,
        menuItemsTree = {CustomerServiceWorkbenchNav.L1_FREESEARCH_PAGE})
public class FreeSearchPage extends BasePage {
    private static final Logger logger = (Logger) LogManager.getLogger(FreeSearchPage.class);

    public FreeSearchPage() {PageFactory.initElements(driver, this);}

    public FreeSearchPage(WebDriver driver)
    {
        super(driver);
    }

    //Free Search Page
    @FindBy(xpath = "//div[@class='tab-label selected']/span[text()='Free Search']")
    private WebElement freeSearchHeaderText;

    //Card Holder Tab

    @FindBy(xpath = "//span[contains(text(),'Cardholder') and @class='ng-binding ng-scope']")
    private WebElement cardHolderTablink;

    @FindBy(xpath = "//input[@name='Name']")
    private WebElement txtNameAndSurname;

    @FindBy(xpath = "//input[@name='PESEL']")
    private WebElement txtPesel;

    @FindBy(xpath = "//input[@name='BirthDate']")
    private WebElement txtBirthDate;

    @FindBy(xpath = "//icon[@class='ng-isolate-scope']/span[@class='block-svg']")
    private WebElement dateIcon;

    @FindBy(xpath = "//button[contains(text(),'Reset')]")
    private WebElement btnResetCardHolder;

    @FindBy(xpath = "//span[contains(text(),'Details') and @class='ng-binding']")
    private WebElement cardHolderDetailsLink;

    //Card Tab

    @FindBy(xpath = "//span[contains(text(),'Card') and @class='ng-binding ng-scope']")
    private WebElement cardTabLink;

    @FindBy(xpath = "//input[@name='ContractNumber']")
    private WebElement txtCardNumber;

    //Account Tab
    @FindBy(xpath = "//span[text()='Account']/ancestor::div[@menu-item-id]")
    private WebElement accountTabLink;

    @FindBy(name = "AccountNumber")
    private WebElement txtAccountNumber;

    @FindBy(name = "RBSNumber")
    private WebElement txtIBAN;

    @FindBy(name = "AccountId")
    private WebElement txtAccountId;

    //Company Tab
    @FindBy(xpath = "//span[contains(text(),'Company') and @class='ng-binding']")
    private WebElement companyTabLink;

    @FindBy(name = "CompanyName")
    private WebElement txtFullCompanyName;

    //Masked Pan Tab
    @FindBy(xpath = "//span[contains(text(),'Masked PAN') and @class='ng-binding']")
    private WebElement maskedPanTabLink;

    @FindBy(name = "MaskedPAN")
    private WebElement txtMaskedPAN;

    @FindBy(xpath = "//button[contains(text(),'Reset')]")
    private WebElement btnReset;

    @FindBy(xpath = "//button[@class='form-save-bar-button search-btn valid-btn']/span[contains(text(),'Find')]")
    private WebElement btnFind;

    @FindBy(xpath = "//span[@class='link-span']/span[contains(text(),'Details')]")
    private WebElement detailsLink;

    public void enterNameAndSurname(String nameAndSurname) {

        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(txtNameAndSurname);
        new WebElementUtility().clearAndSet(txtNameAndSurname, nameAndSurname);
    }

    public void enterCardNumber(String cardNumber) {

        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(txtCardNumber);
        new WebElementUtility().clearAndSet(txtCardNumber, cardNumber);
    }

    public void enterMaskCardNumber(String maskCrdNumber) {

        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(txtMaskedPAN);
        new WebElementUtility().clearAndSet(txtMaskedPAN, maskCrdNumber);
    }

    public FreeSearchPage navigateToCardTab(){

        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(cardTabLink);
//        WebElementUtility.executeJavaScriptOnWebELement(cardTabLink);
        cardTabLink.click();
        return this;
    }

    public FreeSearchPage navigateToAccountTab(){
        accountTabLink.click();
        return this;
    }

    public FreeSearchPage navigateToCompanyTab(){
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(companyTabLink);
        companyTabLink.click();
        return this;
    }

    public FreeSearchPage navigateToCardHolderTab(){

        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(cardHolderTablink);
        cardHolderTablink.click();
        return this;
    }

    public FreeSearchPage navigateToMaskedPANTab(){

        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(maskedPanTabLink);
        maskedPanTabLink.click();
        return this;
    }

    public void enterAccountNumber(String accountNumber) {
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(txtAccountNumber);
        new WebElementUtility().clearAndSet(txtAccountNumber, accountNumber);
    }


    public void enterAccountID(String accountID) {

        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(txtAccountId);
        new WebElementUtility().clearAndSet(txtAccountId, accountID);
    }

    public void enterFullCompanyName(String fullCompanyName) {

        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(txtFullCompanyName);
        new WebElementUtility().clearAndSet(txtFullCompanyName, fullCompanyName);
    }

    public DetailsPage clickDetailsLink(){
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(detailsLink);
        detailsLink.click();
        return new DetailsPage();

    }

    public boolean availabilityOfDetailsLink(){
        try {
            return detailsLink.isDisplayed();
        }
        catch(NoSuchElementException e){
            return false;
        }
    }


}
