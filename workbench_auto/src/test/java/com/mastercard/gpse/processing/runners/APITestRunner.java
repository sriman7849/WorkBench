package com.mastercard.gpse.processing.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features/api/",
        glue = "com.mastercard.gpse.processing.stepDefinitions",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "timeline:target/test-output-thread/"
        }
)
public class APITestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    /*
     TestContext testContext;
     WebDriver webDriver;
     */

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("================ BEFORE SUITE ================");
        //webDriver = testContext.getDriverManager().getDriver();
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("================ AFTER SUITE ================");
        //testContext.getDriverManager().closeDriver();
    }
}
