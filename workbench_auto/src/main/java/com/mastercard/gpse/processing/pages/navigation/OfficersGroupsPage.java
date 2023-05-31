package com.mastercard.gpse.processing.pages.navigation;

import com.mastercard.gpse.processing.pages.BasePage;
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

    @FindBy(xpath = "//input[@name='Name']")
    private WebElement text_name;

    @FindBy(xpath = "//span[contains(text(),'Find')]/ancestor::button")
    WebElement btn_Find;

    @FindBy(xpath = "//input[@name='UserID']")
    private WebElement text_UserID;

    public void clickOnName() {
        new WebElementUtility().clickOnElement(text_name,"By Name");
    }

    public void enterName(String name) {
        new WebElementUtility().clearAndSet(text_name, name);
    }

    public void clickOnUserId() {
        new WebElementUtility().clickOnElement(text_name,"By UserId");
    }

    public void enterUserId(String userId) {
        new WebElementUtility().clearAndSet(text_UserID, userId);
    }
    public void clickOnFindBtn() {
        new WebElementUtility().clickOnElement(btn_Find, "Find");
    }

}
