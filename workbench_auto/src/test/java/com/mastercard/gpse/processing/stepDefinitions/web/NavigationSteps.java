package com.mastercard.gpse.processing.stepDefinitions.web;

import com.mastercard.gpse.processing.managers.Report;
import com.mastercard.gpse.processing.pages.*;
import com.mastercard.gpse.processing.pages.navigation.Navigator;
import com.mastercard.gpse.processing.pages.navigation.OfficersGroupsPage;
import com.mastercard.gpse.processing.utils.SoftAssert;
import com.mastercard.gpse.processing.utils.TestContext;
import com.mastercard.gpse.processing.utils.WaitUtility;
import com.mastercard.gpse.processing.utils.WebElementUtility;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class NavigationSteps {

    private static Logger logger = (Logger) LogManager.getLogger(NavigationSteps.class);
    private TestContext testContext;
    private SoftAssert softAssert;

    public NavigationSteps(TestContext context) {
        this.testContext = context;
        this.softAssert = TestContext.softAssert;
    }

    @Then("user navigates to contracts")
    public void userNavigatesToContracts() {
        new Navigator().navigateToPage(ContractsSearchPage.class);
        Report.info(logger, "navigated to contracts");
    }

    @And("user navigates to {string}")
    public void userNavigatesTo(String arg0) {
        new Navigator().navigateToLinks(arg0);
    }

    @Then("user navigates to the link {string} at the bottom of the tab {string}")
    public void userNavigatesToTheLinkAtTheBottomOfTheTab(String arg0, String arg1) {
        new Navigator().navigateToTabLink(arg1, arg0);
    }

    @And("user navigates to")
    public void userNavigatesTo(DataTable dataTable) {
        String[] menuItems = dataTable.asList().toArray(new String[dataTable.height()]);
        new Navigator().navigateToLinks(menuItems);
    }

    @And("user navigates to clients")
    public void userNavigatesToClients() {
        new Navigator().navigateToPage(ClientPage.class);
        Report.info(logger, "User Navigated to Clients Page");
    }

    @Then("user navigates to Free Search")
    public void userNavigatesToFreeSearch() {
        new Navigator().navigateToPage(FreeSearchPage.class);
        Report.info(logger, "User Navigate to Free search");
    }

    @When("user navigates to Officers")
    public void user_navigates_to_officers() {
        new Navigator().navigateToPage(OfficersPage.class);
        Report.info(logger, "User Navigated to Officers page");
    }


    @And("close {string} tab")
    public void closeTab(String tabTitle) {
        new WebElementUtility().closeTab(tabTitle);
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
    }

    @Then("user navigates to Officer Groups")
    public void userNavigatesToOfficerGroups() {
        new Navigator().navigateToPage(OfficersGroupsPage.class);
        Report.info(logger, "User Navigated to Officers Groups page");
    }

    @When("user navigates to Custom Profiles")
    public void user_navigates_to_custom_profiles() {
        new Navigator().navigateToPage(CustomProfilesPage.class);
        Report.info(logger, "User Navigated to Custom Profiles");
    }

}

