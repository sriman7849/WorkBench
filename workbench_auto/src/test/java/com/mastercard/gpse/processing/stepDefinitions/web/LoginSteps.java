package com.mastercard.gpse.processing.stepDefinitions.web;

import com.mastercard.gpse.processing.constants.Constants;
import com.mastercard.gpse.processing.managers.Report;
import com.mastercard.gpse.processing.pages.HomePage;
import com.mastercard.gpse.processing.pages.LoginPage;
import com.mastercard.gpse.processing.utils.SoftAssert;
import com.mastercard.gpse.processing.utils.TestContext;
import com.mastercard.gpse.processing.utils.WaitUtility;
import com.mastercard.gpse.processing.workflowsui.LoginWorkFlow;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class LoginSteps {

    private static final Logger logger = (Logger) LogManager.getLogger(LoginSteps.class);
    private TestContext testContext;
    private SoftAssert softAssert;
    private LoginPage loginPage;

    public LoginSteps(TestContext context) {
        this.testContext = context;
        this.softAssert = TestContext.softAssert;
    }

    @Given("Login into workbench application")
    public void loginPageIsDisplayed() {
        Report.info(logger, Thread.currentThread().getStackTrace()[1].getMethodName());
        new LoginWorkFlow().loginToCSWAsClerkUser();
    }

    @When("user login workbench application for role {string}")
    public void userEntersTheCredentialsForUserRole(String userRole) {
        switch (userRole) {
            case "Auditor":
                new LoginWorkFlow().loginToCSWAsAuditorUser();
                break;
            case "Clerk":
                new LoginWorkFlow().loginToCSWAsClerkUser();
                break;
            case "Administrator":
                new LoginWorkFlow().loginToCSWAsAdministratorUser();
                break;
            case "Issuer Admin":
                new LoginWorkFlow().loginToCSWAsIssuerAdminUser();
                break;
            case "Lost and Stolen (NEW)":
                new LoginWorkFlow().loginToCSWForLostAndStolenNonPCIRole();
                break;
            case "Lost and Stolen (NEW) PCI-Less":
                new LoginWorkFlow().loginToCSWForLostAndStolenPCILessRole();
                break;
            case "Pay Later":
                new LoginWorkFlow().loginToCSWForPayLaterRole();
                break;
            case "Pay Later PCI-Less":
                new LoginWorkFlow().loginToCSWForPayLaterPCILessRole();
                break;
            case "Pay Later Instalments":
                new LoginWorkFlow().loginToCSWForPayLaterInstalmentsNonPCIRole();
                break;
            case "Pay Later Instalments PCI-Less":
                new LoginWorkFlow().loginToCSWForPayLaterInstalmentsPCILessRole();
                break;
            case "Pay Now":
                new LoginWorkFlow().loginToCSWForPayNowNonPCIRole();
                break;
            case "Pay Now Edit PCI-Less":
                new LoginWorkFlow().loginToCSWForPayNowEditPCILessRole();
                break;
            case "Pay Now PCI-Less":
                new LoginWorkFlow().loginToCSWForPayNowPCILessRole();
                break;
            case "Risk - Pay Later":
                new LoginWorkFlow().loginToCSWForRiskPayLaterNonPCIRole();
                break;
            case "Risk - Pay Later PCI-Less":
                new LoginWorkFlow().loginToCSWForRiskPayLaterPCILessRole();
                break;
            case "Risk - Pay Later Instalments":
                new LoginWorkFlow().loginToCSWForRiskPayLaterInstalmentsNonPCIRole();
                break;
            case "Risk - Pay Later Instalments (PCI-Less)":
                new LoginWorkFlow().loginToCSWForRiskPayLaterInstalmentsPCILessRole();
                break;
            case "Risk Manager Lite":
                new LoginWorkFlow().loginToCSWForRiskManagerLiteNonPCIRole();
                break;
            case "Risk Manager Lite (PCI-Less)":
                new LoginWorkFlow().loginToCSWForRiskManagerLitePCILessRole();
                break;
            case "Transaction Management":
                new LoginWorkFlow().loginToCSWForTxnManagementNonPCIRole();
                break;
            case "Transaction Management (PCI-Less)":
                new LoginWorkFlow().loginToCSWForTxnManagementPCILessRole();
                break;
            case "Risk - Pay Now Edit (PCI-Less)":
                new LoginWorkFlow().loginToCSWForRiskPayNowEditPCILessRole();
                break;
            default:
                Report.fail(logger, "Invalid user role.");
                break;
        }
    }

    @Then("Validate home page is available")
    public void homePageDisplayed() {
        softAssert.assertEquals(new HomePage().getBannerText(), Constants.HOME_PAGE_BANNER);
    }

    @Then("User click on User icon and lock the user manually")
    public void userClickOnUserIconAndLockTheUserManually() {
        new HomePage().lockUser();
    }

    @When("User unlocked successfully")
    public void userUnlockedSuccessfully() {
        softAssert.assertEquals(new HomePage().getBannerText(), Constants.HOME_PAGE_BANNER);
        Report.info(logger, "User unlocked successfully-HomePage displayed");
    }

    @And("validate availability of lock button")
    public void validateAvailabilityOfLockButton() {
        new HomePage().clickUserIcon();
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        softAssert.assertTrue(new HomePage().isLockButtonDisplayed(), "Lock Button is available");
    }

    @When("Logout from Workbench")
    public void logout() {
        new LoginWorkFlow().logout();
        Report.info(logger, "Logout from Workbench");
    }

    @Given("Login into workbench application with wrong Password")
    public void login_into_workbench_application_with_wrong_password() {
        new LoginWorkFlow().loginWithWrongPassword();
        Report.info(logger, "Unsuccessful login");
    }

    @Then("Validate error message")
    public void validateErrorMessage() {
        loginPage = testContext.getPageObjectManager().getPage(LoginPage.class);
        softAssert.assertEquals(loginPage.getErrorMessageText(), Constants.LOGIN_ERROR_MESSAGE);
    }

    @Then("Validate Login page is available")
    public void validateLoginPageIsAvailable() {
        loginPage = testContext.getPageObjectManager().getPage(LoginPage.class);
        softAssert.assertEquals(loginPage.getPageTitle(), Constants.LOGIN_PAGE_TITLE);

    }

    @Then("unlock logged in user")
    public void unlockLoggedinUser() {
        new LoginWorkFlow().loginToCSWAsLockedUser();
    }

    @Then("validate lock button is available again for user")
    public void validateLockButtonIsAvailableAgainForUser() {
        new HomePage().clickUserIcon();
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        softAssert.assertTrue(new HomePage().isLockButtonDisplayed(), "Lock Button Available again");
    }
}