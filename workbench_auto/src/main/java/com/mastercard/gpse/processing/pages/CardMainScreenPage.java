package com.mastercard.gpse.processing.pages;

import com.mastercard.gpse.processing.constants.BankConstants.INBANKConstants;
import com.mastercard.gpse.processing.constants.GlobalConstants;
import com.mastercard.gpse.processing.enums.FieldsEnum;
import com.mastercard.gpse.processing.enums.GroupEnum;
import com.mastercard.gpse.processing.enums.TabTitleEnum;
import com.mastercard.gpse.processing.managers.Report;
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
public class CardMainScreenPage extends BasePage {

    @FindBy(xpath = "//span[text()='Card Data' and @class='ng-binding']")
    private WebElement cardData;

    @FindBy(xpath = "//span[text()='Set Status' and @class='ng-binding']")
    private WebElement setStatus;

    @FindBy(xpath = "//span[text()='Switch On' and @class='ng-binding']")
    private WebElement switchOn;

    @FindBy(xpath = "//span[text()='Switch Off' and @class='ng-binding']")
    private WebElement switchOff;

    @FindBy(xpath = "//div[@name='NewStatus']")
    private WebElement newStatusDD;

    @FindBy(xpath = "//div[@name='NewStatus']//span[text()='PickUp L 41']")
    private WebElement newStatusValueP2;

    @FindBy(xpath = "//div[@class='form-label' and @title='Reason']/ancestor::field-block//div[@ng-class='{open:$select.open}']")
    private WebElement newStatusReasonDD;

    @FindBy(xpath = "//span[contains(@ng-bind-html,'highlight: $select.search') and text() = 'Bank decision']/ancestor::div[contains(@ng-attr-id,'ui-select-choices-row')]")
    private WebElement newStatusReasonvalue;

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    private WebElement saveButton;

    @FindBy(xpath = "//span[text()='Billing History' and @ng-bind-html='getTabTitle(data) | sanitize']")
    private WebElement billingHistryTitle;

    @FindBy(xpath = "//span[text()='Interest Calculation' and @ng-bind-html='getTabTitle(data) | sanitize']")
    private WebElement interestCalculationTitle;

    @FindBy(xpath = "//span[text()='Get Clear PAN' and @class='ng-binding']")
    private WebElement getClearPan_btn;

    @FindBy(xpath = "//span[@class='confirm-inner ']")
    private WebElement getClearPanAlertMessage;

    @FindBy(xpath = "//span[text()='Instalments' and @ng-bind-html='getTabTitle(data) | sanitize']")
    private WebElement instalmentsillingHistryTitle;

    @FindBy(xpath = "(//span[contains(text(),'Billing History')])[4]")
    private WebElement tabBillingHistoryOfFinancials;

    @FindBy(xpath = "//div[contains(@class,'content-element')]")
    private WebElement billingHistoryDetailsTableOfFinancials;

    @FindBy(xpath = "//div[@title='Comment']/ancestor::field-block//div[@class='form-value']//input")
    private WebElement commentTextBox;

    @FindBy(xpath = "//span[text()='Close Instalment Plans' and @class='ng-binding']")
    private WebElement closeInsctalmentPlans_btn;

    @FindBy(xpath = "//button[contains(text(),'Done')]")
    private WebElement dateSelectedDone;

    @FindBy(xpath = "//button[contains(text(),'Done')]")
    private WebElement switchOffDay;

    @FindBy(xpath = "//div[@title='Card Number']//following-sibling::div//input[@type='text']")
    WebElement cardNumberText;

    public CardMainScreenPage() {
        PageFactory.initElements(driver, this);
    }

    public CardMainScreenPage(WebDriver driver) {
        super(driver);
    }

    public String getcardDataText() {
        return new WebElementUtility().getElementText(cardData);
    }

    public String isBillingHistryTitleDisplayed() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        return new WebElementUtility().getElementText(billingHistryTitle);
    }

    public String isinterestCalculationTitleDisplayed() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        return new WebElementUtility().getElementText(interestCalculationTitle);
    }

    public void navigateToGetClearPan() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new WebElementUtility().clickOnElement(getClearPan_btn);
    }

    public boolean isGetClearPanButtonisdisplayed() {
        return new WebElementUtility().isElementVisible(getClearPan_btn);
    }

    public String isgetClearPanAlertMessageDisplayed() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        return new WebElementUtility().getElementText(getClearPanAlertMessage);
    }

    public String getValueOf(String fieldName) {
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression("//div[@title='%s']/ancestor::field-block//div[@class='form-value']//input", fieldName));
        return field.getAttribute("value");
    }

    public String isInstalmentsTitleDisplayed() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        return new WebElementUtility().getElementText(instalmentsillingHistryTitle);
    }

    /**
     * It will Convert the Middle Letters of  to Characters
     * Example - 5429110000992098 To 5429********2098
     */
    public static String hideMiddleDigits(String input) {
        if (input == null || input.length() < 16) {
            return input;
        }
        char[] chars = input.toCharArray();
        for (int i = 4; i < 12; i++) {
            chars[i] = '*';
        }
        return new String(chars);
    }

    public void clickOnBillingHistoryOfFinancials() {
        new WebElementUtility().clickOnElement(tabBillingHistoryOfFinancials, "Billing History");
    }

    public int getBillingHistoryRecord() {
        return new WebElementUtility().getTableObject(billingHistoryDetailsTableOfFinancials).getRowCount();
    }

    public void setStatusForCardData(String value) {
        new CommonPage().selectDropDown(FieldsEnum.NEW_STATUS.getValue());
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new CommonPage().selectDropDownValue(value);
    }

    public void selectCardDataNewStatusReason(String value) {
        new CommonPage().selectDropDown(FieldsEnum.REASON.getValue());
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new CommonPage().selectDropDownValue(value);
    }


    public void selectCardDataSwitchOffReasonCode(String value) {
        new CommonPage().selectDropDown(FieldsEnum.REASON.getValue());
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new CommonPage().selectDropDownValue(value);
    }

    public void enterCardDataSwitchOffComment(String comment) {
        new WebElementUtility().clearAndSet(commentTextBox, comment);
    }

    public void selectCardDataSwitchOnReasonCode(String value) {
        new CommonPage().selectDropDown(FieldsEnum.REASON.getValue());
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new CommonPage().selectDropDownValue(value);
    }

    public void enterCardDataSwitchOnComment(String comment) {
        new WebElementUtility().clearAndSet(commentTextBox, comment);
    }
    public void selectCardDataRedefineReasonCode(String value)
    {
        new CommonPage().selectDropDown(FieldsEnum.REASON.getValue());
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new CommonPage().selectDropDownValue(value);
    }

    public void enterCardDataRedefineComment(String comment)
    {
        new WebElementUtility().clearAndSet(commentTextBox,comment);
    }
    public void selectCardDataRestoreOriginalReasonCode(String value)
    {
        new CommonPage().selectDropDown(FieldsEnum.REASON.getValue());
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new CommonPage().selectDropDownValue(value);
    }

    public void enterCardDataRestoreOriginalComment(String comment)
    {
        new WebElementUtility().clearAndSet(commentTextBox, comment);
    }

    public void ClickCloseInstalmentPlansButton() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new WebElementUtility().clickOnElement(closeInsctalmentPlans_btn);
    }

    public void switchOffOneDay()
    {
        new WebElementUtility().clickOnElement(dateSelectedDone);

    }

    public void enterCardNumber(String cardNumber) {
            new WebElementUtility().clearAndSet(cardNumberText, cardNumber);
        }

    }

