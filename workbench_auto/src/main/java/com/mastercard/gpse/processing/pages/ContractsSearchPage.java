package com.mastercard.gpse.processing.pages;

import com.mastercard.gpse.processing.managers.Report;
import com.mastercard.gpse.processing.pages.navigation.CustomerServiceWorkbenchNav;
import com.mastercard.gpse.processing.pages.navigation.Navigation;
import com.mastercard.gpse.processing.utils.WaitUtility;
import com.mastercard.gpse.processing.utils.WebElementUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Navigation(tabTitle = CustomerServiceWorkbenchNav.TAB_TITLE,
        menuItemsTree = {CustomerServiceWorkbenchNav.L1_CONTRACTS_PAGE})
public class ContractsSearchPage extends BasePage {

    private static Logger logger = (Logger) LogManager.getLogger(ContractsSearchPage.class);

    @FindBy(xpath = "//span[text()='By Bank Account Number']/ancestor::div[@menu-item-id]")
    WebElement byBankAccountNumberTab;

    @FindBy(xpath = "//span[text()='By Contract ID']/ancestor::div[@menu-item-id]")
    WebElement byContractIDTab;

    @FindBy(xpath = "//div[@title='Contract ID']//following-sibling::div//input[@type='text']")
    WebElement contractIDText;

    @FindBy(xpath = " //div[@title='Bank Account Number']//following-sibling::div//input[@type='text']")
    WebElement bankAccountNumberText;

    @FindBy(xpath = " //div[@title='Bank Code']//following-sibling::div//input[@type='text']")
    WebElement bankCodeEnterText;

    @FindBy(xpath = "//span[contains(text(),'Find')]/ancestor::button")
    WebElement findButton;

    @FindBy(xpath = "//*[@id=\"query-bar\"]/div[1]/div/span/span")
    WebElement byNumberTab;

    @FindBy(xpath = "//div[@title='Contract Number']//following-sibling::div//input[@type='text']")
    WebElement contractNumberText;

    @FindBy(xpath = "//div[@class='label-body']//span[text()='by CBS Number']")
    WebElement byCBSNumberTab;

    @FindBy(xpath = "//div[contains(@class,'content-element')]")
    WebElement contractsDetailTable;

    @FindBy(xpath = "//span[@class='link-span']/span[contains(text(),'Details')]")
    WebElement detailsLink;

    @FindBy(xpath = "//div[@title='CBS Number']//following-sibling::div//input[@type='text']")
    WebElement cbsNumberText;

    @FindBy(xpath = "//span[text()='Details']/ancestor::tab//div[contains(@class,'group-label') and text()='General']/ancestor::div[contains(@class,'first-group')]//div[@title='Bank Code']/ancestor::field-block//div[@class='form-value']//input")
    WebElement bankCodeGetTxt;

    public ContractsSearchPage() {
        super();
    }

    public ContractsSearchPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnByContractIDTab() {
        new WebElementUtility().clickOnElement(byContractIDTab, "By Contract ID");
        Report.info(logger, "Clicked on By Contract ID");
    }

    public void enterContractID(String contractID) {
        new WebElementUtility().clearAndSet(contractIDText, contractID);
        Report.info(logger, "Enter Contract ID: " + contractID);
    }

    public void clickOnFind() {
        new WebElementUtility().clickOnElement(findButton, "Find");
    }

    public void clickOnByNumberTab() {
        new WebElementUtility().clickOnElement(byNumberTab, "By Number");
    }

    public void enterContractNumber(String contractNumber) {
        new WebElementUtility().clearAndSet(contractNumberText, contractNumber);
    }

    public void clickOnByCBSNumberTab() {
        new WebElementUtility().clickOnElement(byCBSNumberTab, "By CBS Number");
    }

    public void enterCBSNumber(String cbsNumber) {
        new WebElementUtility().clearAndSet(cbsNumberText, cbsNumber);
    }

    public int getContractTableRecordsCount() {
        return new WebElementUtility().getTableObject(contractsDetailTable).getRowCount();
    }

    public void enterBankAccountNumberAndCode(String BankAccountNumber,String bankCode) {
        new WebElementUtility().clearAndSet(bankAccountNumberText, BankAccountNumber);
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new WebElementUtility().clearAndSet(bankCodeEnterText,bankCode);
    }

    public boolean availabilityOfDetailsLink() {
        try {
            return new WebElementUtility().isElementVisible(detailsLink);
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public String getBankCodeFromContractScreen(){
        return new WebElementUtility().getElementValueAttributeValue(bankCodeGetTxt);
    }

}