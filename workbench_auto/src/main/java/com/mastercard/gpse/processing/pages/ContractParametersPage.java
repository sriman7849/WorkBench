package com.mastercard.gpse.processing.pages;

import com.mastercard.gpse.processing.utils.StringUtility;
import com.mastercard.gpse.processing.utils.WebElementUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContractParametersPage extends BasePage {

    public ContractParametersPage(){
        PageFactory.initElements(driver,this);
    }
    public ContractParametersPage(WebDriver driver) {
        super(driver);
    }

    public void selectContractParameter(String contractParameterName)
    {
        String locator= "//span[contains(text(),'%s')]//parent::div";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator,contractParameterName));
        new WebElementUtility().clickOnElement(field,contractParameterName);
    }

}
