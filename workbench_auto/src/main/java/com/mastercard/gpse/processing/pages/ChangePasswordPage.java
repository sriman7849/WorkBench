package com.mastercard.gpse.processing.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ChangePasswordPage extends BasePage{
    private static final Logger logger = (Logger) LogManager.getLogger(ChangePasswordPage.class);
    
    public ChangePasswordPage(){
        PageFactory.initElements(driver,this);
    }

    public ChangePasswordPage(WebDriver driver) {
        super(driver);
    }
}
