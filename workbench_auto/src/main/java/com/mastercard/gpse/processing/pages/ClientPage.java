package com.mastercard.gpse.processing.pages;

import com.mastercard.gpse.processing.enums.FieldsEnum;
import com.mastercard.gpse.processing.enums.StatusEnum;
import com.mastercard.gpse.processing.pages.navigation.CustomerServiceWorkbenchNav;
import com.mastercard.gpse.processing.pages.navigation.Navigation;
import com.mastercard.gpse.processing.utils.WaitUtility;
import com.mastercard.gpse.processing.utils.WebElementUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Navigation(tabTitle = CustomerServiceWorkbenchNav.TAB_TITLE,
        menuItemsTree = {CustomerServiceWorkbenchNav.L1_CLIENTS_PAGE})
public class ClientPage extends BasePage {

    @FindBy(xpath = "//div[@class='br-step ng-scope step-hide first br-tab selected']")
    WebElement client;

    @FindBy(xpath = "//input[@name='ClientNumber']")
    WebElement text_clientNumber;

    @FindBy(xpath = "//span[contains(text(),'Find')]/ancestor::button")
    WebElement button_Find;

    @FindBy(xpath = "//span[@class='link-drag-label']")
    WebElement ByIdentityCardNumber;

    @FindBy(xpath = "//input[@name='IdentityCardNumber']")
    WebElement text_IdentityCardNumber;

    @FindBy(xpath = "//input[@name='SearchString']")
    WebElement text_FindBasicSearch;

    @FindBy(xpath = "(//span[contains(text(),'Details')])[1]")
    WebElement tab_details;

    @FindBy(xpath = "//input[@name='ID']")
    WebElement text_clientID;

    @FindBy(xpath = "//span[@class='link-span']/span[contains(text(),'Details')]")
    WebElement detailsLink;

    @FindBy(xpath = " //div[@title='Memo Type']")
    WebElement memos;

    @FindBy(xpath = "//div[contains(@class,'slick-row active')]//child::div[contains(@class,'slick-cell')]//child::span[contains(@data-title,'Original Message')]")
    WebElement originalMessageText;

    @FindBy(xpath = "(//div[contains(@id,'gridElement')])[2]")
    WebElement memosTable;

    @FindBy(xpath = "//div[@class='tab-label br-step br-right2 br-right-query-bar br-menu-button icon-links tab-label-settings icon-links-hovered']/child::icon")
    WebElement Form_editor;

    @FindBy(xpath = "//span[@title='Client Number']//span[@class='cell-content']")
    WebElement field_CilentNumber;

    @FindBy(xpath = "//span[@title='Client Number']//span[@class='field-hidden-true']")
    WebElement clientNumberFieldHidden;

    @FindBy(xpath = "//span[@title='Client Number']//span[@class='field-hidden-false']")
    WebElement clientNumberFieldNotHidden;

    @FindBy(xpath = "//input[@value='false']")
    WebElement radioBtnNo;

    @FindBy(xpath = "//input[@value='true']")
    WebElement radioBtnYes;

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    WebElement btnSave;

    @FindBy(xpath = "//div[contains(@class,'panel animate pane_details')]//div[@title=\"Close\"]")
    WebElement btncross;

    @FindBy(xpath = "//div[contains(@class,'content-element')]")
    WebElement clientsDetailTable;

    @FindBy(xpath = "//span[@ng-bind-html='getTabTitle(data) | sanitize' and text()='GDPR']/ancestor::tab//div[@icons-bar]//div[@class='tab-label-top-button for-focus']//span[contains(@ng-bind-html,'getLinkTitle(link)') and text()='Set Consents']")
    WebElement setGDPRMarketingConsentsButton;

    public ClientPage() {
        PageFactory.initElements(driver, this);
    }

    public ClientPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnClientNumber() {
        new WebElementUtility().clickOnElement(text_clientNumber, "By Client Number");
    }

    public void enterClientNumber(String clientNumber) {
        new WebElementUtility().clearAndSet(text_clientNumber, clientNumber);
    }

    public boolean isClientNumberFieldDisplayed() {
        try {
            return new WebElementUtility().isElementVisible(text_clientNumber);
        } catch (Exception ex) {
            return false;
        }
    }

    public void clickOnIdentityCardNumber() {
        new WebElementUtility().clickOnElement(text_IdentityCardNumber, "IDENTITY CARD NUMBER");
    }

    public void enterIdentityCardNumber(String IdentityCardNumber) {
        new WebElementUtility().clearAndSet(text_IdentityCardNumber, IdentityCardNumber);
    }

    public void enterBasicName(String BasicName) {
        new WebElementUtility().clearAndSet(text_FindBasicSearch, BasicName);
    }

    public void enterClientID(String ClientID) {
        new WebElementUtility().clearAndSet(text_clientID, ClientID);
    }

    public void clickOnFind() {
        new WebElementUtility().clickOnElement(button_Find, "Find");
    }

    public boolean getTextOfDetailsTab() {
        try {
            return new WebElementUtility().isElementVisible(tab_details);
        } catch (Exception Ex) {
            return false;
        }
    }

    public boolean availabilityOfDetailsLink() {
        return new WebElementUtility().isElementVisibleWithoutWarning(detailsLink);
    }

    public boolean isMemoTypeAvailable() {
        return new WebElementUtility().isElementVisible(memos);
    }

    public String getMessageFromMemoPage() {
        return new WebElementUtility().getElementValueAttributeValue(originalMessageText);
    }

    public boolean getMemosTableStatus() {
        boolean flag = false;
        Map<String, String> rowCellData = new WebElementUtility().getTableObject(memosTable).getRowData(1);
        if (rowCellData.containsValue(StatusEnum.CLOSED.getValue())) {
            flag = true;
        }
        return flag;
    }

    public void clickOnFormEditor() {
        new WebElementUtility().clickOnElement(Form_editor, "Form editor");
    }

    public void clickOnFieldClientNumber() {
        new WebElementUtility().clickOnElement(field_CilentNumber, "Client Number");
    }

    public boolean isClientNumberFieldHidden() {
        return new WebElementUtility().isElementVisible(clientNumberFieldHidden);
    }

    public boolean isClientNumberFieldNotHidden() {
        return new WebElementUtility().isElementVisible(clientNumberFieldNotHidden);
    }

    public void clickOnRadioBtnNo() {
        new WebElementUtility().clickOnElement(radioBtnNo);
    }

    public void clickOnRadioBtnYes() {
        new WebElementUtility().clickOnElement(radioBtnYes);
    }

    public void clickOnCrossButton() {
        new WebElementUtility().clickOnElement(btncross);
    }

    public int getClientTableRecordsCount() {
        return new WebElementUtility().getTableObject(clientsDetailTable).getRowCount();
    }

    public void clickOnSetGDPRMarketingConsentsButton() {
        new WebElementUtility().clickOnElement(setGDPRMarketingConsentsButton, "set consents");
    }
}



