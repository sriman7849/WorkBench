package com.mastercard.gpse.processing.runners;

import com.mastercard.gpse.processing.managers.SSHTunnel;
import com.mastercard.gpse.processing.pages.LoginPage;
import com.mastercard.gpse.processing.workflowsapi.WBPreRequisite;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features/web/",
        glue = "com.mastercard.gpse.processing.stepDefinitions",
        tags = "@AartiPatel",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty.html",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "timeline:target/test-output-thread/",
        },
        publish = true
)
public class WebTestRunner extends AbstractTestNGCucumberTests {

    SSHTunnel sshTunnel;

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("================ BEFORE SUITE ================");
        // Create the pre requisite test data using the API calls.
        sshTunnel = new SSHTunnel();
        sshTunnel.getSSHTunnelSession();
        sshTunnel.sendKeepAliveMsg();
        new WBPreRequisite().createPreRequisite();
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("================ AFTER SUITE ================");
        sshTunnel.disconnectSSHTunnel();
        LoginPage.webDriver.close();
    }
}

