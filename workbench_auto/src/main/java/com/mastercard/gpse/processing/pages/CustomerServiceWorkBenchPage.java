package com.mastercard.gpse.processing.pages;

import com.mastercard.gpse.processing.pages.navigation.CustomerServiceWorkbenchNav;
import com.mastercard.gpse.processing.pages.navigation.Navigation;
import com.mastercard.gpse.processing.utils.WebElementUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Navigation(tabTitle = CustomerServiceWorkbenchNav.TAB_TITLE,
        menuItemsTree = {})
public class CustomerServiceWorkBenchPage extends BasePage {
    private static final Logger logger = (Logger) LogManager.getLogger(CustomerServiceWorkBenchPage.class);

    public CustomerServiceWorkBenchPage(){
        PageFactory.initElements(driver,this);
    }

    public CustomerServiceWorkBenchPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//span[@text()='Customer Service Workbench']")
    private WebElement headerTitle;

    @FindBy(xpath = "//span[@text()='Home']")
    private WebElement homePagelink;

    @FindBy(xpath = "//div[@class = 'br-step ng-scope step-hide selected first br-tab']/span[contains(text(),'Clients')]")
    private WebElement clientsPagelink;

    @FindBy(xpath = "//div[@class='br-step ng-scope step-hide br-tab']/span[text()='Contracts']")
    private WebElement contractPagelink;

    @FindBy(xpath = "//div[@class='br-step ng-scope step-hide br-tab step-last']/span[text()='Free Search']")
    private WebElement freeSearchPagelink;

/*
    public ClientsPage navigateToClientsPage(){
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(clientsPagelink);
        clientsPagelink.click();
        return new ClientsPage();
    }

    public ContractPage navigateToContractPage(){
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(contractPagelink);
        contractPagelink.click();
        return new ContractPage();
    }
*/

    public void navigateToFreeSearchPage(){
        new WebElementUtility().waitForElementToBeClickableAfterPageLoading(freeSearchPagelink);
        freeSearchPagelink.click();

    }

}
