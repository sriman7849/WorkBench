package com.mastercard.gpse.processing.pages;

import com.mastercard.gpse.processing.managers.Report;
import com.mastercard.gpse.processing.pages.navigation.HomeNav;
import com.mastercard.gpse.processing.pages.navigation.Navigation;
import com.mastercard.gpse.processing.utils.WaitUtility;
import com.mastercard.gpse.processing.utils.WebElementUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Navigation(tabTitle = HomeNav.TAB_TITLE, menuItemsTree = {})
public class HomePage extends BasePage{
    private static final Logger logger = (Logger) LogManager.getLogger(HomePage.class);
    public HomePage(){

        PageFactory.initElements(driver,this);
    }

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(text(),'Customer Service Workbench')]")
    private WebElement cswLink;

    @FindBy(xpath = "//div[contains(text(),'Risk Case Configuration')]")
    private WebElement riskCaseConfigurationLink;

    @FindBy(xpath = "//div[contains(text(),'Dispute Case Configuration')]")
    private WebElement disputeCaseConfigurationLink;

    @FindBy(xpath = "//div[@class = 'title ng-binding' and contains(text(),'Officers and Grants')]")
    private WebElement officersAndGrantsLink;

    @FindBy(xpath = "//div[contains(text(),'MPTS - WAY4 Web 3.0')]")
    private WebElement bannerText;

    @FindBy(xpath = "//div[text()='Logout']")
    private WebElement logoutLink;

    @FindBy(css = "icon.user-icon.ng-isolate-scope")
    private WebElement userIcon;
    
    @FindBy(xpath = "(//div[contains(text(),'Lock')])[1]")
    private WebElement lockButton;

    public String getPageTitle(){
        return driver.getTitle();
    }

    public String getBannerText(){
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(bannerText);
        return bannerText.getText();
    }

    public void clickCswLink(){
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(cswLink);
        cswLink.click();
    }

    public void clickOfficersAndGrantsLink(){
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(officersAndGrantsLink);
        officersAndGrantsLink.click();
    }

 /*   public CustomerServiceWorkBenchPage clickCswLink(){
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(cswLink);
        cswLink.click();
        return new CustomerServiceWorkBenchPage();
    }*/

   /* public RiskCaseConfigurationPage clickRccLink() {
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(riskCaseConfigurationLink);
        riskCaseConfigurationLink.click();
        return new RiskCaseConfigurationPage();
    }*/

/*    public OfficersAndGrantsPage clickOfficersAndGrantsLink(){
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(officersAndGrantsLink);
        officersAndGrantsLink.click();
        return new OfficersAndGrantsPage();
    }*/

    public String getPageSourceCode(){
        return driver.getPageSource();
    }

    public void clickUserIcon(){

        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(userIcon);
        userIcon.click();
        Report.info(logger,"Click on User Icon");
        //return this;
    }

    public void signOut(){

        clickUserIcon();
        logoutLink.click();
        //return new LoginPage();
    }

    public boolean isLockButtonDisplayed() {
        try {
            return lockButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void lockUser() {
        clickUserIcon();
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(lockButton);
        lockButton.click();
    }

    public boolean availabilityOfCSWPageLink(){
        try {
            return cswLink.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public boolean availabilityOfRiskCaseConfigurationLinkPageLink(){
        try {
            return riskCaseConfigurationLink.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public boolean availabilityOfDisputeCaseConfigurationPageLink(){
        try {
            return disputeCaseConfigurationLink.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public boolean availabilityOfOfficersAndGrantsLinkPageLink(){
        try {
            return officersAndGrantsLink.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public void navigateToDCCPage()
    {
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(disputeCaseConfigurationLink);
        disputeCaseConfigurationLink.click();
        new WaitUtility().threadDotSleep(1000);
    }

}
