package com.mastercard.gpse.processing.managers;

import com.mastercard.gpse.processing.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {

    /*private final WebDriver webDriver;

    public PageObjectManager(WebDriver webDriver) {
        this.webDriver = webDriver;
    }*/

    AllDriverManager webDriver= new AllDriverManager();

    public <T extends BasePage> T getPage(Class<T> pageClass) {
        T page = null;
        try {
            page = pageClass.getConstructor(WebDriver.class).newInstance(webDriver.getDriver());
        } catch (Exception e) {
            //logger.error(e.getMessage(), e);
            throw new RuntimeException("Fail to create page object: " + pageClass.getName(), e);
        }

        return page;
    }
}
