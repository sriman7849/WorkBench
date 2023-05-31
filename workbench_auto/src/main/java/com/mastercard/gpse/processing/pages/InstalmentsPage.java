package com.mastercard.gpse.processing.pages;

import com.mastercard.gpse.processing.enums.FieldsEnum;
import com.mastercard.gpse.processing.utils.WaitUtility;
import com.mastercard.gpse.processing.utils.WebElementUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InstalmentsPage extends BasePage {

    @FindBy(xpath = "//div[contains(@ng-click,'actionClick')]//child::span[contains(text(),'Instalment Plan By Balance')]")
    WebElement instalmentPlanByBalanceButton;

    @FindBy(xpath = "//div[contains(@ng-click,'actionClick')]//child::span[contains(text(),'Close Instalment Plans')]")
    WebElement closeInstalmentPlanButton;

    @FindBy(xpath = "//div[@name='ReasonCode']")
    WebElement reasonDD;


    public InstalmentsPage() {
        PageFactory.initElements(driver, this);
    }

    public InstalmentsPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnInstalmentPlanByBalanceButton() {
        new WebElementUtility().clickOnElement(instalmentPlanByBalanceButton, "Instalment Plan By Balance");
    }

    public void clickOnCloseInstalmentPlanButton() {
        new WebElementUtility().clickOnElement(closeInstalmentPlanButton, "Close Instalment Plans");
    }

    public void selectCloseInstalmentReasonCode(String value) {
        new CommonPage().selectDropDown(FieldsEnum.REASON.getValue());
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        new CommonPage().selectDropDownValue(value);
    }

}
