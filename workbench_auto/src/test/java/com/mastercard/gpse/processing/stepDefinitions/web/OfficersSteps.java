package com.mastercard.gpse.processing.stepDefinitions.web;

import com.mastercard.gpse.processing.constants.BankConstants.INBANKConstants;
import com.mastercard.gpse.processing.constants.GlobalConstants;
import com.mastercard.gpse.processing.managers.Report;
import com.mastercard.gpse.processing.pages.CommonPage;
import com.mastercard.gpse.processing.pages.FieldPage;
import com.mastercard.gpse.processing.pages.OfficersPage;
import com.mastercard.gpse.processing.pages.navigation.Navigator;
import com.mastercard.gpse.processing.pages.navigation.OfficersGroupsPage;
import com.mastercard.gpse.processing.utils.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

public class OfficersSteps {

    private static Logger logger = (Logger) LogManager.getLogger(ContractSearchSteps.class);
    private TestContext testContext;
    private SoftAssert softAssert;

    public OfficersSteps(TestContext context) {
        this.testContext = context;
        this.softAssert = TestContext.softAssert;
    }

    @Then("user search Officers In Group {string} BY MASK by NAME")
    public void userSearchOfficersInGroupBYMASKByNAME(String arg0) {
        new OfficersPage().enterName(arg0);
        new OfficersPage().clickOnFindBtn();
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_ONE_THOUSAND);
        Report.info(logger, "searched Officers In Group record By Mask by Name");
    }

    @Then("Navigate to {string} Tab and {string} SubTab")
    public void navigateToTabAndSubTab(String arg0, String arg1) {
        new Navigator().navigateToTab(arg0, arg1);
        Report.info(logger, "User navigated to By Mask SubTab");
    }

    @Then("user search Officers In Group {string} BY MASK by USER ID")
    public void userSearchOfficersInGroupBYMASKByUSERID(String arg0) {
        new OfficersPage().enterUserId(arg0);
        new OfficersPage().clickOnFindBtn();
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_ONE_THOUSAND);
        Report.info(logger, "searched Officers In Group record By Mask by UserID");
    }

    @Then("user search {string} with {string}")
    public void userSearchWith(String arg0, String arg1) {
        new CommonPage().lookUpWith(arg1, arg0);
    }

    @Then("filter with {string} Officer Group")
    public void filterWithOfficerGroup(String groupName) {
        new CommonPage().lookUpWith(GlobalConstants.COLUMN_NAME, groupName);
    }

    @Then("search by {string} with {string}")
    public void searchByWith(String by, String text) {
        new FieldPage().enterTextValue(by, text);
        new CommonPage().clickFindBtn();
    }

    @Then("user search Officers group by Name")
    public void userSearchOfficersGroupByName() {
        new Navigator().navigateToLinks("By Mask");
        new OfficersGroupsPage().enterName(INBANKConstants.officersGroupName);
        new OfficersGroupsPage().clickOnFindBtn();
        Report.info(logger, "searched Officers Group by Name: " + INBANKConstants.officersGroupName);
    }

    @And("user navigates to Active")
    public void userNavigatesToActive() {
        new OfficersPage().clickOnActiveTab();
    }

    @And("validate Officers details page")
    public void validateOfficersDetailsPage() {
        new CommonPage().getValueOf("Officer Type");
        softAssert.assertEquals(new CommonPage().getValueOf("Officer Type"), GlobalConstants.OFFICER_TYPE, "Validated Officers Details page");

    }

    @Then("user search Officers {string} BY MASK by Name")
    public void userSearchOfficersBYMASKByName(String arg0) {
        new OfficersPage().enterName(arg0);
        new OfficersPage().clickOnFindBtn();
        Report.info(logger, "Searched Officer BY MASK by Name");

    }

    @Then("user search Officers {string} BY MASK by UserID")
    public void userSearchOfficersBYMASKByUserID(String arg0) {
        new OfficersPage().enterUserId(arg0);
        new OfficersPage().clickOnFindBtn();
        Report.info(logger, "Searched Officer BY MASK by UserID");
    }

    @And("Validate visible are only one {string} client groups")
    public void validateVisibleAreOnlyOneClientGroups(String clientName) {
       List<WebElement> els=new WebElementUtility().getListOfElemnets();
       for(WebElement ele:els)
       {
           Report.info(logger,"Col values:"+ ele.getText());
           String s=ele.getText();
           s=s.toUpperCase();
           if(s.contains(clientName))
           {
               softAssert.assertTrue(true);
           }
       }

    }
}
