package com.mastercard.gpse.processing.utils;

import com.mastercard.gpse.processing.managers.AllDriverManager;
import com.mastercard.gpse.processing.managers.PageObjectManager;

public class TestContext {

    private final AllDriverManager driverManager;
    private final PageObjectManager pageObjectManager;
    public ScenarioContext scenarioContext;
    public static SoftAssert softAssert;

    public TestContext() {
        driverManager = new AllDriverManager();
        pageObjectManager = new PageObjectManager();
        scenarioContext = new ScenarioContext();
        softAssert = new SoftAssert();
    }

    public AllDriverManager getDriverManager() {
        return driverManager;
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}
