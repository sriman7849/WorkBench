package com.mastercard.gpse.processing.pages;

import com.mastercard.gpse.processing.utils.WaitUtility;
import com.mastercard.gpse.processing.utils.WebElementUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LoginPage extends BasePage {
    private static final Logger logger = (Logger) LogManager.getLogger(LoginPage.class);


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "login")
    private WebElement txtUserName;

    @FindBy(name = "password")
    private WebElement txtPassword;

    @FindBy(name = "otpassword")
    private WebElement txtTwoFactorCode;

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    private WebElement loginButton;
    ////div[text()='Lock']/parent::div/following-sibling::div/icon/following-sibling::div

    @FindBy(xpath = "//button[contains(text(),'Unlock')]")
    private WebElement unlockButton;

    @FindBy(linkText = "Change your password?")
    private WebElement changePasswordLink;

    @FindBy(xpath = "//div[contains(text(),'Web Workbenches')]")
    private WebElement bannerText;

    @FindBy(xpath = "//span[contains(text(),'Invalid login or password')]")
    private WebElement errorMessageText;

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void enterUserName(String userName) {
        new WaitUtility().waitForElementToBeVisible(txtUserName,WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_SIXTY_THOUSAND);
        new WebElementUtility().clearAndSet(txtUserName, userName);
    }

    public boolean availabilityOfUnlockButton() {
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(unlockButton);
        return unlockButton.isDisplayed();
    }

    public void clickUnlockButton() {
        unlockButton.isDisplayed();
        unlockButton.click();
        //return new HomePage();
    }

    public String getUserName() {
        //new WebElementUtility().waitForElementToBeClickableAfterPageLoading(txtUserName);
        return new WebElementUtility().getElementValueAttributeValue(txtUserName);
        //return txtUserName.getText();

    }

    public String getErrorMessageText() {
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(errorMessageText);
        return errorMessageText.getText();
    }

    public void enterPassword(String password) {
        new WaitUtility().waitForElementToBeVisible(txtPassword,WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_SIXTY_THOUSAND);
        new WebElementUtility().clearAndSet(txtPassword, password);
    }

    public void enterTwoFactorCode(String twoFactorCode) {
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(txtTwoFactorCode);
        new WebElementUtility().clearAndSet(txtTwoFactorCode, twoFactorCode);
    }


    public String getBannerText() {
        return bannerText.getText();
    }

    public LoginPage open(String url) {
            try {
                driver.manage().timeouts().pageLoadTimeout(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_SIXTY_THOUSAND, TimeUnit.MILLISECONDS);
                getDriver().get(url);

            }catch(TimeoutException e)
            {
                getDriver().get(url);
            }

        return this;
    }

    public ChangePasswordPage clickForgotPasswordLink() {
        changePasswordLink.click();
        return new ChangePasswordPage();
    }

    public void clickLoginButton() {
        loginButton.click();
        //new WebElementUtility().threadDotSleep(10000);

    }

    public void login(String url, String userName, String password,String twoFactorCode) {
        open(url);
        enterUserName(userName);
        enterPassword(password);
        try{
            enterTwoFactorCode(twoFactorCode);
        }
        catch (Exception e){
            logger.error("Issue with Two Factor Code: "+e.getMessage());
        }
        clickLoginButton();

    }
    public void unlock(String password,String twoFactorCode) {
        enterPassword(password);
        try{
            enterTwoFactorCode(twoFactorCode);
        }
        catch (Exception e){
            logger.error("Issue with Two Factor Code: "+e.getMessage());
        }
        clickUnlockButton();
    }
}