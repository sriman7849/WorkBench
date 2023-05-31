package com.mastercard.gpse.processing.pages;

import com.mastercard.gpse.processing.pages.navigation.Navigation;
import com.mastercard.gpse.processing.pages.navigation.OfficersAndGrantsNav;
import com.mastercard.gpse.processing.utils.StringUtility;
import com.mastercard.gpse.processing.utils.WebElementUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Navigation(tabTitle = OfficersAndGrantsNav.TAB_TITLE,
        menuItemsTree = {OfficersAndGrantsNav.L1_OFFICER_GROUPS_PAGE})
public class OfficersGroupsPage extends BasePage {

    public OfficersGroupsPage() {
        PageFactory.initElements(driver, this);
    }

    public OfficersGroupsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(@menu-item-id,'Password')]//div//span")
    private WebElement btnSetPassword;

    @FindBy(xpath = "//div[@id='WSOffDtlsResetFailedLoginCount']/span[@class='link-span ng-binding' and @ng-bind-html='getLinkTitle(link) | sanitize' and text()='Reset Failed Login Count']")
    private WebElement btnResetFailedLoginCount;

    public void clickOnSetPasswordBtn(String tabTitle) {
        String locator = "//span[@ng-bind-html='getTabTitle(data) | sanitize' and text()='%s']/ancestor::tab//div[contains(@menu-item-id,'Password')]//div";
        WebElement setPasswordButton = new WebElementUtility().locateElement("xpath", String.format(locator, tabTitle));
        new WebElementUtility().clickOnElement(setPasswordButton, "Set Password");

    }

    public void clickOnResetFailedLoginCountBtn(String tabTitle) {
        String locator = "//span[@ng-bind-html='getTabTitle(data) | sanitize' and text()='%s']/ancestor::tab//div[contains(@menu-item-id,'ResetFailedLoginCount')]//span[text()='Reset Failed Login Count']";
        WebElement ResetFailedLoginCountBtn= new WebElementUtility().locateElement("xpath", String.format(locator, tabTitle));
        new WebElementUtility().clickOnElement(ResetFailedLoginCountBtn, "Reset Failed Login Count");
    }

    public void clickOnResetFailedLoginBtnOnDetailsPage(){
       new WebElementUtility().JSClickOnElement(btnResetFailedLoginCount);
    }
}
