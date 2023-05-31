package com.mastercard.gpse.processing.pages;

import com.mastercard.gpse.processing.utils.WebElementUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BillingHistoryPage extends BasePage{

    public BillingHistoryPage(){
        PageFactory.initElements(driver,this);
    }
    public BillingHistoryPage(WebDriver driver) {
        super(driver);
    }


}
