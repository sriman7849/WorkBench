package com.mastercard.gpse.processing.pages;

import com.mastercard.gpse.processing.pages.navigation.CustomerServiceWorkbenchNav;
import com.mastercard.gpse.processing.pages.navigation.Navigation;
import com.mastercard.gpse.processing.utils.StringUtility;
import com.mastercard.gpse.processing.utils.WaitUtility;
import com.mastercard.gpse.processing.utils.WebElementUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Navigation(tabTitle = CustomerServiceWorkbenchNav.TAB_TITLE,
        menuItemsTree = {CustomerServiceWorkbenchNav.L1_FREESEARCH_PAGE,})
public class ContractPage  extends BasePage {

    public ContractPage(){
        PageFactory.initElements(driver,this);
    }
    public ContractPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[contains(@ng-bind-html,\"getTabTitle\") and contains(text(),'%s')]/ancestor::tab//div")
    WebElement pageTable;

    @FindBy(xpath = "//span[text()='Set Parameter' and @class='ng-binding']")
    private WebElement setParameters;

    @FindBy(xpath = "//span[text()='Clear' and @class='ng-binding']")
    private WebElement clear;

    public void ClickOnSetParameters(){
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        setParameters.click();
    }

    public void ClickOnClear(){
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        clear.click();
    }

    public void ClickOnClassifiers(){
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(setParameters);
        setParameters.click();
    }

    public void clickOnSetAdditionalOrTemporaryFinancialLimit(String str)
    {
        String locator="//div[contains(text(),'Set %s Limit')]/parent::div[contains(@class,'br-menu-item')]";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator,str));
        new WebElementUtility().clickOnElement(field,"Set "+str+" Limit");
    }

}
