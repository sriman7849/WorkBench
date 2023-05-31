package com.mastercard.gpse.processing.pages;

import com.mastercard.gpse.processing.utils.StringUtility;
import com.mastercard.gpse.processing.utils.WebElementUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RiskControlsPage extends BasePage {

    public RiskControlsPage(){
        PageFactory.initElements(driver,this);
    }
    public RiskControlsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[contains(@ng-if,'true')]")
    private WebElement tabTitle ;

    public String getTabTitleText(){
        return new WebElementUtility().getElementText(tabTitle);
    }
    public void selectRiskRulesCode(String riskRulesCode)
    {
        String locator= "//span[contains(text(),'%s')]//parent::div";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator,riskRulesCode));
        new WebElementUtility().clickOnElement(field,riskRulesCode);
    }
}
