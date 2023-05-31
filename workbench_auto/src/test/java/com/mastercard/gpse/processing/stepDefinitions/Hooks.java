package com.mastercard.gpse.processing.stepDefinitions;

import com.mastercard.gpse.processing.managers.SSHTunnel;
import com.mastercard.gpse.processing.utils.SoftAssert;
import com.mastercard.gpse.processing.utils.TestContext;
import com.mastercard.gpse.processing.workflowsui.LoginWorkFlow;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class Hooks {

    TestContext testContext;
    WebDriver webDriver;
    SSHTunnel sshTunnel = new SSHTunnel();
    SoftAssert softAssert = TestContext.softAssert;

    public Hooks(TestContext context) {
        testContext = context;
    }

    @Before
    public void setUp() {
        System.out.println("================ BEFORE HOOK ================");
        webDriver = testContext.getDriverManager().getDriver();
        sshTunnel.getSSHTunnelSession();
        sshTunnel.sendKeepAliveMsg();
        //webDriver.get(FileReaderManager.getInstance().getConfigFileReader().getUrl());
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("================ AFTER HOOK ================");
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            } catch (WebDriverException noSupportScreenshot) {
                System.err.println(noSupportScreenshot.getMessage());
                System.out.println("Test Failed- Screenshot not Supported");
            }
        }
        new LoginWorkFlow().logout();
//       softAssert.assertAll();
    }
}
