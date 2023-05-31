package com.mastercard.gpse.processing.pages;

import com.mastercard.gpse.processing.managers.AllDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;

public class BasePage extends AllDriverManager {

    //private static final Logger LOG = Logger.getLogger(BasePage.class);
    protected WebDriver driver;
    public static HashMap<String,String> testContext = new HashMap<>();

    public BasePage() {
        driver = getDriver();
        PageFactory.initElements(driver, this);
    }

    public BasePage(WebDriver driver) {

        if (driver == null) {
            throwNoDriverException();
        }

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }
    private void throwNoDriverException() {
        throw new RuntimeException(
                "There seems to be no window open, please verify if the browser is still open.");
    }
}
