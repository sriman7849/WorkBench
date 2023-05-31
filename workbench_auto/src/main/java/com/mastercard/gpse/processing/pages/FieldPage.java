package com.mastercard.gpse.processing.pages;

import com.mastercard.gpse.processing.utils.StringUtility;
import com.mastercard.gpse.processing.utils.WebElementUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FieldPage extends BasePage{

    private static final Logger logger = (Logger) LogManager.getLogger(HomePage.class);

    public FieldPage(){

        PageFactory.initElements(driver,this);
    }
    public FieldPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Example - Enter value in text box field 'First Name', 'Last Name', etc.
     *
     * @param fieldName - field name
     * @param  text- you wan't to enter
     */
    public void enterTextValue(String fieldName,String text) {
        String locator = "//div[@title='%s']/ancestor::field-block//div[@class='form-value']//input";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator, fieldName));
        new WebElementUtility().clearAndSet(field,text);
    }

    /**
     * Example - Enter value in text box field 'First Name', 'Last Name', etc.
     *
     * @param  group - group name
     * @param fieldName - field name
     * @param  text- you wan't to enter
     */
    public void enterTextValue(String group,String fieldName,String text) {
        String locator = " //div[contains(@class,'group-label') and text()='%s']/ancestor::div[contains(@class,'first-group')]//div[@title='%s']/ancestor::field-block//div[@class='form-value']//input";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator,group,fieldName));
        new WebElementUtility().clearAndSet(field,text);
    }

    /**
     * Example - Enter value in text box field 'First Name', 'Last Name', etc.
     *
     * @param  title - tab title name
     * @param  group - group name
     * @param fieldName - field name
     * @param  text- you wan't to enter
     */
    public void enterTextValue(String title,String group,String fieldName,String text) {
        String locator = "//span[@ng-bind-html='getTabTitle(data) | sanitize' and text()='%s']/ancestor::tab//div[contains(@class,'group-label') and text()='%s']/ancestor::div[contains(@class,'first-group')]//div[@title='%s']/ancestor::field-block//div[@class='form-value']//input";
        WebElement field = new WebElementUtility().locateElement("xpath", StringUtility.formatExpression(locator,title,group,fieldName));
        new WebElementUtility().clearAndSet(field,text);
    }
}
