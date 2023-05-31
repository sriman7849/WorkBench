package com.mastercard.gpse.processing.pages;

import com.mastercard.gpse.processing.constants.GlobalConstants;
import com.mastercard.gpse.processing.enums.FieldsEnum;
import com.mastercard.gpse.processing.managers.Report;
import com.mastercard.gpse.processing.utils.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.JavascriptExecutor;
import com.mastercard.gpse.processing.utils.DateUtility;
import com.mastercard.gpse.processing.utils.StringUtility;
import com.mastercard.gpse.processing.utils.Table;
import com.mastercard.gpse.processing.utils.WaitUtility;
import com.mastercard.gpse.processing.utils.WebElementUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

public class CommonPage extends BasePage {

    private static final Logger logger = (Logger) LogManager.getLogger(CommonPage.class);

    @FindBy(xpath = "//div[contains(@class,'content-element')]//ancestor::div[contains(@class,'has-top-links')]")
    WebElement pageTable;

    @FindBy(xpath = "//span[@class='popup-text info']")
    WebElement popUpDialog;

    @FindBy(xpath = " //button[contains(text(),'Save')]")
    WebElement save_Btn;

    @FindBy(xpath = "//button[text()='OK']")
    WebElement OK_BTN;

    @FindBy(xpath = "//span[contains(text(),'Set Parameter')]//parent::div")
    WebElement setParameter_BTN;

    @FindBy(xpath = "//div[@title='Comment']/ancestor::field-block//div[@class='form-value']//input")
    private WebElement commentTextBox;

    @FindBy(xpath = "//span[contains(text(),'more') and contains(@class,'label link')]")
    WebElement more_Link;

    @FindBy(xpath = "//span[text()='Switch On' and @class='ng-binding']")
    private WebElement switchOn_BTN;

    @FindBy(xpath = "//div[contains(@ng-if,'edit')]")
    private WebElement editIcon;

    @FindBy(xpath = "//button[text()='Done']")
    private WebElement done_BTN;

    @FindBy(xpath = "//button[@ng-enter='resetForm()' and contains(text(),'Reset')]")
    private WebElement reset_BTN;

    @FindBy(xpath = "//span[@class='ng-binding ng-scope' and contains(text(),'Find')]")
    private WebElement find_BTN;

    @FindBy(xpath = "//span[text()='Set Classifier' and @class='ng-binding']")
    private WebElement setClassifiers_Btn;

    @FindBy(xpath = "//icon[contains(@el,'sys-create')]//parent::div[contains(@title,'Create')]")
    WebElement createMemos;

    @FindBy(xpath = "//select-full[contains(@value,'itemValue')]//child::div//child::div[contains(@name,'ReasonCode')]")
    WebElement reasonDD;

    @FindBy(xpath = "//select-full[contains(@value,'itemValue')]//child::div//child::div[contains(@name,'MemoType')]")
    WebElement memoTypeDD;

    @FindBy(xpath = "//span[contains(@class,'ui-select-choices')]")
    WebElement selectValueDD;

    @FindBy(xpath = "//textarea[@name='OriginalMessage']")
    WebElement originalMessage;

    @FindBy(xpath = "//icon[contains(@el,'sys-edit')]//parent::div[contains(@title,'Edit')]")
    WebElement editMemos;

    @FindBy(xpath = "//icon[contains(@el,'sys-delete')]//parent::div[contains(@title,'Remove')]")
    WebElement deleteMemos;

    public CommonPage() {
        PageFactory.initElements(driver, this);
    }

    public CommonPage(WebDriver driver) {
        super(driver);
    }

    public boolean isColumnPresentInTable(String columnName) {
        String locator = "//div[@title='%s']//span[@class='slick-column-name']";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, columnName));
        return new WebElementUtility().isElementVisible(field);
    }

    public void lookUpWith(String columnName, String text) {
        clickOnLookUpIcon(columnName);
        enterTextInSearchBox(columnName, text);
        clickOnOkLookUp(columnName);
    }

    public void clickOnOkLookUp(String columnName) {
        String locator = "//div[@id='gridElement']//div[@title='%s']//div[contains(@class,'slick-column-filtered')]/following::icon[@ng-click='vm.close()']";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, columnName));
        new WebElementUtility().JSClickOnElement(field);
    }

    public void enterTextInSearchBox(String columnName, String text) {
        String locator = "//div[@id='gridElement']//div[@title='%s']//div[contains(@class,'slick-column-filtered')]/following::input[@dir='auto']";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, columnName));
        new WebElementUtility().clearAndSet(field, text);
    }

    public void clickOnLookUpIcon(String columnName) {
        String locator = "//div[@id='gridElement']//div[@title='%s']//div[contains(@class,'slick-column-filtered')]/*[@class='filter-apply-button']";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, columnName));
        new WebElementUtility().clickOnElement(field);
    }

    public Table getTableObject(String tabTitle) {
        String locator = "//span[@ng-bind-html='getTabTitle(data) | sanitize' and text()='%s']/ancestor::tab//div[contains(@id,'gridElement')]";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, tabTitle));
        Table table = new Table(field);
        return table;
    }

    public void clickOnSetParameterButton() {
        new WebElementUtility().clickOnElement(setParameter_BTN, "Set Parameter");
    }

    public void clickOnSwitchOnButton() {
        new WebElementUtility().clickOnElement(switchOn_BTN, "Switch On");
    }

    public void selectDropDown(String ddTitle) {
        String locator = "//div[@class='form-label' and @title='%s']/ancestor::field-block//div[@ng-class='{open: $select.open}']";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, ddTitle));
        new WebElementUtility().clickOnElement(field, ddTitle);
    }

    public void selectDropDownValue(String text) {
        String locator = "//span[contains(@ng-bind-html,'highlight: $select.search') and text() = '%s']/ancestor::div[contains(@ng-attr-id,'ui-select-choices-row')]";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, text));
        new WebElementUtility().clickOnElement(field, text);
    }

    public String selectDropDownValueByIndex(int index) {
        String locator = "//span[contains(@ng-bind-html,'highlight: $select.search')]/ancestor::div[contains(@ng-attr-id,'ui-select-choices-row')]['%d']";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, index));
        String text = new WebElementUtility().getElementText(field);
        new WebElementUtility().clickOnElement(field, String.valueOf(index));
        return text;
    }

    /**
     * Method is used to Click on a paticular row by passing row name
     */
    public void selectPaticularRow(String name, String coloumnName) {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_THREE_THOUSAND);
        String locator = "//span[text()='%s' and @class='cell-content']//parent::div//span[@data-title='%s']";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, name, coloumnName));
        new WebElementUtility().JSClickOnElement(field);
    }


    public String getPopUpDialogText() {
        return new WebElementUtility().getElementText(popUpDialog);
    }

    /**
     * Method is used to get table record count
     *
     * @return recordCount;
     */
    public int getTableRecordsCount() {
        new WaitUtility().waitForElementToBeVisible(pageTable);
        return new WebElementUtility().getTableObject(pageTable).getRowCount();
    }

    /**
     * Method is used to get table record count
     *
     * @return recordCount;
     */
    public int getTableRecordsCount(String tabTitle) {
        return new WebElementUtility().getTableObject(tabTitle).getRowCount();
    }

    /**
     * Method is used to return tab title
     *
     * @param tabName
     * @return tab tab title
     */
    public String getTabTitleText(String tabName) {
        String locator = "//span[@ng-bind-html='getTabTitle(data) | sanitize' and  text()='%s']";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, tabName));
        return new WebElementUtility().getElementText(field);
    }

    /**
     * Return the value of the provided field name from the client main screen page.<br>
     * Example - read the value of field 'First Name', 'Last Name', etc.
     *
     * @param fieldName - field name whose value is required
     * @return value of the field
     */
    public String getValueOf(String fieldName) {
        String locator = "//div[@title='%s']/ancestor::field-block//div[@class='form-value']//input";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, fieldName));
        return field.getAttribute("value");
    }

    /**
     * Return the value of the provided field name from the client main screen page
     * Example - read the value of field 'First Name', 'Last Name', etc. under the given sub-headers/groups like 'Personal Details', 'Embossed Parms' etc.
     *
     * @param group     - sub-header/group
     * @param fieldName - field name whose value is required
     * @return value of the field
     */
    public String getValueOf(String group, String fieldName) {
        String locator = " //div[contains(@class,'group-label') and text()='%s']/ancestor::div[contains(@class,'first-group')]//div[@title='%s']/ancestor::field-block//div[@class='form-value']//input";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, group, fieldName));
        return field.getAttribute("value");
    }

    /**
     * Return the value of the provided field name from the client main screen page
     * Example - read the value of field 'First Name', 'Last Name', etc. under the given sub-headers/groups like 'Personal Details', 'Embossed Parms' etc.
     *
     * @param title     - Tab title
     * @param group     - sub-header/group
     * @param fieldName - field name whose value is required
     * @return value of the field
     */
    public String getValueOf(String title, String group, String fieldName) {
        String locator = "//span[@ng-bind-html='getTabTitle(data) | sanitize' and text()='%s']/ancestor::tab//div[contains(@class,'group-label') and text()='%s']/ancestor::div[contains(@class,'first-group')]//div[@title='%s']/ancestor::field-block//div[@class='form-value']//input";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, title, group, fieldName));
        return field.getAttribute("value");
    }

    /**
     * Return the value of the provided field name from the client main screen page
     * Example - read the value of field 'First Name', 'Last Name', etc. under the given sub-headers/groups like 'Personal Details', 'Embossed Parms' etc.
     *
     * @param screenCode
     * @param title      - Tab title
     * @param group      - sub-header/group
     * @param fieldName  - field name whose value is required
     * @return value of the field
     */
    public String getValueOf(String screenCode, String title, String group, String fieldName) {
        String locator = "//span[@ng-bind-html='getTabTitle(data) | sanitize' and text()='%s']/ancestor::div[contains(@code,'%s')]/ancestor::tab//div[contains(@class,'group-label') and text()='%s']/ancestor::div[contains(@class,'first-group')]//div[@title='%s']/ancestor::field-block//div[@class='form-value']//input";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, title, screenCode, group, fieldName));
        return field.getAttribute("value");
    }

    /**
     * Return the value of the provided field name from the client main screen page
     * Example - read the value of field 'First Name', 'Last Name', etc. under the given sub-headers/groups like 'Personal Details', 'Embossed Parms' etc.
     *
     * @param group     - sub-header/group
     * @param fieldName - field name whose value is required
     * @return value of the field
     */
    public String getValueOfTextArea(String group, String fieldName) {
        String locator = " //div[contains(@class,'group-label') and text()='%s']/ancestor::div[contains(@class,'first-group')]//div[@title='%s']/ancestor::field-block//div[@class='textarea-readonly-field-container ng-scope']//textarea";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, group, fieldName));
        return field.getAttribute("value");
    }

    /**
     * Return the titleAttribute of the provided field name from the card main screen page
     * Example - read the titleAttribute of field 'First Name', 'Last Name', etc. under the given sub-headers/groups like 'Personal Details', 'Embossed Parms' etc.
     *
     * @param group     - sub-header/group
     * @param fieldName - field name whose value is required
     * @return titleAttribute of the field
     */
    public String getTitleOfTextArea(String group, String fieldName) {
        String locator = " //div[contains(@class,'group-label') and text()='%s']/ancestor::div[contains(@class,'first-group')]//div[@title='%s']/ancestor::field-block//div[@class='textarea-readonly-field-container ng-scope']//textarea";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, group, fieldName));
        return field.getAttribute("title");
    }

    public void enterTextIn(String fieldName, String value) {
        String locator = "//div[@class='form-label-text ng-binding' and normalize-space()='Contract Number']/ancestor::field-block//input";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, fieldName));
        new WebElementUtility().enterText(field, value);
    }

    public void clickOnSaveBtn() {
        new WebElementUtility().clickOnElement(save_Btn, "Save Button");
    }

    public void clickOnOKBtn() {
        new WebElementUtility().clickOnElement(OK_BTN, "OK Button");
    }

    public void enterComment(String comment) {
        new WebElementUtility().clearAndSet(commentTextBox, comment);
    }

    public void closeTab(String tabTitle) {
        String locator = "//span[@ng-bind-html='getTabTitle(data) | sanitize' and text()='%s']/ancestor::tab//div[@title='Close']";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, tabTitle));
        new WebElementUtility().clickOnElement(field);
        Report.info(logger, "Closed '" + tabTitle + "' tab");
    }

    public void clickOnMoreLink() {
        new WebElementUtility().clickOnElement(more_Link);
    }

    public void clickOnEditIconBtn() {
        new WebElementUtility().clickOnElement(editIcon, "Edit Icon");
    }

    public void clickOnDoneBtn() {
        new WebElementUtility().clickOnElement(done_BTN);
    }

    public void clickOnResetBtn() {
        new WebElementUtility().clickOnElement(reset_BTN);
    }

    public void clickFindBtn() {
        new WebElementUtility().clickOnElement(find_BTN);
    }

    public void ClickSetClassifiersButton() {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new WebElementUtility().clickOnElement(setClassifiers_Btn);
    }

    public void clickOnCreateMemos() {
        new WebElementUtility().clickOnElement(createMemos, "Memos");
    }

    public void selectMemosReason() {
        new WaitUtility().waitForElementToBeVisible(reasonDD, WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new DropDown().selectDropDownByVisibleText(FieldsEnum.REASON.getValue(), GlobalConstants.REASON_DD_VALUE_BANK_DECISION);
    }

    public void selectMemoType() {
        new WaitUtility().waitForElementToBeVisible(memoTypeDD, WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new DropDown().selectDropDownByVisibleText(FieldsEnum.MEMO_TYPE.getValue(), GlobalConstants.STANDARD_MEMO);
    }

    public String enterOriginalMessage(String message) {
        new WebElementUtility().clearAndSet(originalMessage, message);
        return new WebElementUtility().getElementValueAttributeValue(originalMessage);
    }

    public void editMemos() {
        new WebElementUtility().clickOnElement(editMemos, "Edit Memos");
    }

    public void deleteMemos() {
        new WebElementUtility().clickOnElement(deleteMemos, "Delete Memos");
    }
}


    

