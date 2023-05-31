package com.mastercard.gpse.processing.workflowsui;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.mastercard.gpse.processing.fileoperations.TestProperties;
import com.mastercard.gpse.processing.pages.HomePage;
import com.mastercard.gpse.processing.pages.LoginPage;
import com.mastercard.gpse.processing.constants.Constants;
import com.mastercard.gpse.processing.utils.TOTPGenerator;
import com.mastercard.gpse.processing.utils.WaitUtility;
import com.mastercard.gpse.processing.utils.WebElementUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.Assert;

public class LoginWorkFlow extends AbstractWorkflow {

    private static final Logger logger = (Logger) LogManager.getLogger(LoginWorkFlow.class);
    public LoginPage loginPage;
    public HomePage homePage;

    /**
     * Role - Auditor
     */
    public void loginToCSWAsAuditorUser() {
        login(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("auditor.username"),
                TestProperties.getInstance().getUserProperty("auditor.password"),
                TestProperties.getInstance().getUserProperty("auditor.otpKey"));
    }

    /**
     * Role - Clerk
     *
     * @return - Home page
     */
    public HomePage loginToCSWAsClerkUser() {
        login(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("clerk.username"),
                TestProperties.getInstance().getUserProperty("clerk.password"),
                TestProperties.getInstance().getUserProperty("clerk.otpKey"));
        return new HomePage();
    }

    public HomePage loginWithWrongPassword() {
        UnsuccessfulLogin(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("role.riskPayLaterInstallment.username"),
                TestProperties.getInstance().getUserProperty("invalid.password"),
                TestProperties.getInstance().getUserProperty("role.riskPayLaterInstallment.otpKey"));
        return new HomePage();
    }

    public HomePage loginToCSWAsLockedUser() {
        unlock(TestProperties.getInstance().getUserProperty("password.clerk"),
                TestProperties.getInstance().getUserProperty("username.clerk.otpKey"));
        return new HomePage();


    }

    /**
     * Role - Administrator
     */
    public void loginToCSWAsAdministratorUser() {
        login(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("administrator.username"),
                TestProperties.getInstance().getUserProperty("administrator.password"),
                TestProperties.getInstance().getUserProperty("administrator.otpKey"));
    }

    /**
     * Role - Issuer Admin
     */
    public void loginToCSWAsIssuerAdminUser() {
        login(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("role.issuer.admin.username"),
                TestProperties.getInstance().getUserProperty("role.issuer.admin.password"),
                TestProperties.getInstance().getUserProperty("role.issuer.admin.otpKey"));
    }

    /**
     * Role - Lost and Stolen (NEW)
     */
    public void loginToCSWForLostAndStolenNonPCIRole() {
        login(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("role.lostStolen.username"),
                TestProperties.getInstance().getUserProperty("role.lostStolen.password"),
                TestProperties.getInstance().getUserProperty("role.lostStolen.otpKey"));
        testContext.put(Constants.ROLE_PCI_LESS, "FALSE");
    }

    /**
     * Role - Lost and Stolen (NEW) PCI - Less
     */
    public void loginToCSWForLostAndStolenPCILessRole() {
        login(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("role.lostStolen.pci.less.username"),
                TestProperties.getInstance().getUserProperty("role.lostStolen.pci.less.password"),
                TestProperties.getInstance().getUserProperty("role.lostStolen.pci.less.otpKey"));
        testContext.put(Constants.ROLE_PCI_LESS, "TRUE");
    }

    /**
     * Role - Pay Later
     */
    public void loginToCSWForPayLaterRole() {
        login(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("role.paylater.username"),
                TestProperties.getInstance().getUserProperty("role.paylater.password"),
                TestProperties.getInstance().getUserProperty("role.paylater.otpKey"));
        testContext.put(Constants.ROLE_PCI_LESS, "FALSE");
    }

    /**
     * Role - Pay Later PCI-Less
     */
    public void loginToCSWForPayLaterPCILessRole() {
        login(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("role.paylater.pci.less.username"),
                TestProperties.getInstance().getUserProperty("role.paylater.pci.less.password"),
                TestProperties.getInstance().getUserProperty("role.paylater.pci.less.otpKey"));
        testContext.put(Constants.ROLE_PCI_LESS, "TRUE");
    }

    /**
     * Role - Pay Later Instalments
     */
    public void loginToCSWForPayLaterInstalmentsNonPCIRole() {
        login(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("role.paylater.instalments.username"),
                TestProperties.getInstance().getUserProperty("role.paylater.instalments.password"),
                TestProperties.getInstance().getUserProperty("role.paylater.instalments.otpKey"));
        testContext.put(Constants.ROLE_PCI_LESS, "FALSE");
    }

    /**
     * Role - Pay Later Instalments PCI-Less
     */
    public void loginToCSWForPayLaterInstalmentsPCILessRole() {
        login(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("role.paylater.instalments.pci.less.username"),
                TestProperties.getInstance().getUserProperty("role.paylater.instalments.pci.less.password"),
                TestProperties.getInstance().getUserProperty("role.paylater.instalments.pci.less.otpKey"));
        testContext.put(Constants.ROLE_PCI_LESS, "TRUE");
    }

    /**
     * Role - Pay Now
     */
    public void loginToCSWForPayNowNonPCIRole() {
        login(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("role.paynow.username"),
                TestProperties.getInstance().getUserProperty("role.paynow.password"),
                TestProperties.getInstance().getUserProperty("role.paynow.otpKey"));
        testContext.put(Constants.ROLE_PCI_LESS, "FALSE");
    }

    /**
     * Role - Pay Now PCI-Less
     */
    public void loginToCSWForPayNowPCILessRole() {
        login(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("role.paynow.pci.less.username"),
                TestProperties.getInstance().getUserProperty("role.paynow.pci.less.password"),
                TestProperties.getInstance().getUserProperty("role.paynow.pci.less.otpKey"));
        testContext.put(Constants.ROLE_PCI_LESS, "TRUE");
    }

    /**
     * Role - Pay Now Edit PCI - Less
     */
    public void loginToCSWForPayNowEditPCILessRole() {
        login(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("role.paynow.edit.pci.less.username"),
                TestProperties.getInstance().getUserProperty("role.paynow.edit.pci.less.password"),
                TestProperties.getInstance().getUserProperty("role.paynow.edit.pci.less.otpKey"));
        testContext.put(Constants.ROLE_PCI_LESS, "TRUE");
        testContext.put(Constants.LOGGED_IN_USER, TestProperties.getInstance().getUserProperty("role.paynow.edit.pci.less.username"));
        testContext.put(Constants.PASSWORD, TestProperties.getInstance().getUserProperty("role.paynow.edit.pci.less.password"));
        testContext.put(Constants.OTPKEY, TestProperties.getInstance().getUserProperty("role.paynow.edit.pci.less.otpKey"));
    }

    /**
     * Role - Risk - Pay Later
     */
    public void loginToCSWForRiskPayLaterNonPCIRole() {
        login(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("role.riskPayLaterInstallment.username"),
                TestProperties.getInstance().getUserProperty("role.riskPayLaterInstallment.password"),
                TestProperties.getInstance().getUserProperty("role.riskPayLaterInstallment.otpKey"));
        testContext.put(Constants.ROLE_PCI_LESS, "FALSE");
    }

    /**
     * Role - Risk - Pay Later PCI-Less
     */
    public void loginToCSWForRiskPayLaterPCILessRole() {
        login(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("role.riskPayLater.pci.less.username"),
                TestProperties.getInstance().getUserProperty("role.riskPayLater.pci.less.password"),
                TestProperties.getInstance().getUserProperty("role.riskPayLater.pci.less.otpKey"));
        testContext.put(Constants.ROLE_PCI_LESS, "TRUE");
    }

    /**
     * Role - Risk - Pay Later Instalments
     */
    public void loginToCSWForRiskPayLaterInstalmentsNonPCIRole() {
        login(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("role.riskPayLaterInstallment.username"),
                TestProperties.getInstance().getUserProperty("role.riskPayLaterInstallment.password"),
                TestProperties.getInstance().getUserProperty("role.riskPayLaterInstallment.otpKey"));
        testContext.put(Constants.ROLE_PCI_LESS, "FALSE");
    }

    /**
     * Role - Risk - Pay Later Instalments (PCI-Less)
     */
    public void loginToCSWForRiskPayLaterInstalmentsPCILessRole() {
        login(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("role.riskPayLaterInstallment.pci.less.username"),
                TestProperties.getInstance().getUserProperty("role.riskPayLaterInstallment.pci.less.password"),
                TestProperties.getInstance().getUserProperty("role.riskPayLaterInstallment.pci.less.otpKey"));
        testContext.put(Constants.ROLE_PCI_LESS, "FALSE");
    }

    /**
     * Role - Risk Manager Lite
     */
    public void loginToCSWForRiskManagerLiteNonPCIRole() {
        login(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("role.riskManagerLite.username"),
                TestProperties.getInstance().getUserProperty("role.riskManagerLite.password"),
                TestProperties.getInstance().getUserProperty("role.riskManagerLite.otpKey"));
        testContext.put(Constants.ROLE_PCI_LESS, "FALSE");
    }

    /**
     * Role - Risk Manager Lite (PCI-Less)
     */
    public void loginToCSWForRiskManagerLitePCILessRole() {
        login(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("role.riskManagerLite.pci.less.username"),
                TestProperties.getInstance().getUserProperty("role.riskManagerLite.pci.less.password"),
                TestProperties.getInstance().getUserProperty("role.riskManagerLite.pci.less.otpKey"));
        testContext.put(Constants.ROLE_PCI_LESS, "TRUE");
    }

    /**
     * Role - Transaction Management
     */
    public void loginToCSWForTxnManagementNonPCIRole() {
        login(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("role.txnMgt.username"),
                TestProperties.getInstance().getUserProperty("role.txnMgt.password"),
                TestProperties.getInstance().getUserProperty("role.txnMgt.otpKey"));
        testContext.put(Constants.ROLE_PCI_LESS, "FALSE");
    }

    /**
     * Role - Transaction Management (PCI - Less)
     */
    public void loginToCSWForTxnManagementPCILessRole() {
        login(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("role.txnMgt.pci.less.username"),
                TestProperties.getInstance().getUserProperty("role.txnMgt.pci.less.password"),
                TestProperties.getInstance().getUserProperty("role.txnMgt.pci.less.otpKey"));
        testContext.put(Constants.ROLE_PCI_LESS, "TRUE");
    }

    /**
     * Role - Risk - Pay Now Edit (PCI-Less)
     */
    public void loginToCSWForRiskPayNowEditPCILessRole() {
        login(TestProperties.getInstance().getEnvProperty("workbench.url"),
                TestProperties.getInstance().getUserProperty("role.riskPayNowEdit.pci.less.username"),
                TestProperties.getInstance().getUserProperty("role.riskPayNowEdit.pci.less.password"),
                TestProperties.getInstance().getUserProperty("role.riskPayNowEdit.pci.less.otpKey"));
        testContext.put(Constants.ROLE_PCI_LESS, "TRUE");
    }


    /**
     * @param url
     * @param userName
     * @param password
     * @param twoFactorCode
     * @return
     */
    public String getErrorMsgForIncorrectPassword(String url, String userName, String password, String twoFactorCode) {
        loginPage = pageObjectFactory.getPage(LoginPage.class);
        loginPage.login(url, userName, password, twoFactorCode);
        return loginPage.getErrorMessageText();
    }

    /**
     * @param loggedInUser
     * @return
     */
    public boolean validateErrorMsgForIncorrectPassword(String loggedInUser) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info(methodName);
        String screenShotName = new WebElementUtility().getFileNameForScreenshot(methodName);

        String url = TestProperties.getInstance().getEnvProperty("workbench.url");
        String userName;
        String password = "Random123";
        String twoFactorCode;

        if (loggedInUser.equalsIgnoreCase("clerk")) {
            userName = TestProperties.getInstance().getUserProperty("username.clerk");
            twoFactorCode = TOTPGenerator.getTwoFactorCode(TestProperties.getInstance().getUserProperty("username.clerk.otpKey"));
        } else if (loggedInUser.equalsIgnoreCase("auditor")) {
            userName = TestProperties.getInstance().getUserProperty("username.auditor");
            twoFactorCode = TOTPGenerator.getTwoFactorCode(TestProperties.getInstance().getUserProperty("username.auditor.otpKey"));
        } else if (loggedInUser.equalsIgnoreCase("administrator")) {
            userName = TestProperties.getInstance().getUserProperty("username.administrator");
            twoFactorCode = TOTPGenerator.getTwoFactorCode(TestProperties.getInstance().getUserProperty("password.administrator.otpKey"));
        } else {
            userName = TestProperties.getInstance().getUserProperty("username.role.riskPayNowEdit.pci.less");
            twoFactorCode = TOTPGenerator.getTwoFactorCode(TestProperties.getInstance().getUserProperty("password.role.riskPayNowEdit.pci.less.otpKey"));
        }

        String actualErrorMsg = getErrorMsgForIncorrectPassword(url, userName, password, twoFactorCode);
        String expectedErrorMsg = Constants.LOGIN_ERROR_MESSAGE;
        new WaitUtility().threadDotSleep(3);
        new WebElementUtility().getScreenshot(screenShotName, testContext.get(Constants.TEST_CASE_ID));
        if (expectedErrorMsg.equals(actualErrorMsg)) {
            logger.info("Actual Error Msg: " + actualErrorMsg);
            logger.info("Expected Error Msg: " + expectedErrorMsg);
            return true;
        } else {
            logger.info("Actual Error Msg: " + actualErrorMsg);
            logger.info("Expected Error Msg: " + expectedErrorMsg);
            return false;
        }
    }

    /**
     * @param url
     * @param userName
     * @param password
     * @param otpKey
     */
    public void login(String url, String userName, String password, String otpKey) {
        loginPage = pageObjectFactory.getPage(LoginPage.class);
        loginPage.login(url, userName, password, TOTPGenerator.getTwoFactorCode(otpKey));
        homePage = pageObjectFactory.getPage(HomePage.class);

        logger.info("Home Page: " + homePage.getPageTitle());
        logger.info("Home Page Banner: " + homePage.getBannerText());
        Assert.assertEquals(homePage.getPageTitle(), Constants.HOME_PAGE_TITLE);
        Assert.assertEquals(homePage.getBannerText(), Constants.HOME_PAGE_BANNER);
        logger.info("Login Successful");
    }

    public void unlock(String password, String otpKey) {
        loginPage = pageObjectFactory.getPage(LoginPage.class);
        loginPage.unlock(password, TOTPGenerator.getTwoFactorCode(otpKey));
        homePage = pageObjectFactory.getPage(HomePage.class);
    }

    public void UnsuccessfulLogin(String url, String userName, String password, String otpKey) {
        loginPage = pageObjectFactory.getPage(LoginPage.class);
        loginPage.login(url, userName, password, TOTPGenerator.getTwoFactorCode(otpKey));

    }

    public void validateAvailabilityOfLinksForClerkUser() {
        homePage = pageObjectFactory.getPage(HomePage.class);
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info(methodName);
        String screenShotName = new WebElementUtility().getFileNameForScreenshot(methodName);

        boolean flag;
        flag = homePage.availabilityOfCSWPageLink();
        new WebElementUtility().getScreenshot(screenShotName, testContext.get(Constants.TEST_CASE_ID));
        Assert.assertTrue(flag, "CSW Page link is missing");
        flag = homePage.availabilityOfDisputeCaseConfigurationPageLink();
        Assert.assertTrue(flag, "Dispute Case Configuration Page link is missing");
        flag = homePage.availabilityOfRiskCaseConfigurationLinkPageLink();
        Assert.assertTrue(flag, "Risk Case Configuration Page link is missing");
        flag = homePage.availabilityOfOfficersAndGrantsLinkPageLink();
        Assert.assertTrue(flag, "Officers And Grants Page link is missing");

    }

    /**
     * Logout user
     */
    public void logout() {
        homePage = pageObjectFactory.getPage(HomePage.class);
        homePage.signOut();

        /*logger.info("Login Page: " + loginPage.getPageTitle());
        logger.info("Login Page Banner: " + loginPage.getBannerText());*/

        //Assert.assertEquals(loginPage.getPageTitle(), Constants.LOGIN_PAGE_TITLE);
        //Assert.assertEquals(loginPage.getBannerText(), Constants.LOGIN_PAGE_BANNER);
        logger.info("Logout Successful");

        //return loginPage;
    }

    /**
     * @return
     */
    public boolean validateLockFunctionality() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info(methodName);
        String screenShotName = new WebElementUtility().getFileNameForScreenshot(methodName);

        homePage.lockUser();
        new WaitUtility().threadDotSleep(10);

        boolean flag = loginPage.availabilityOfUnlockButton();
        new WebElementUtility().getScreenshot(screenShotName, testContext.get(Constants.TEST_CASE_ID));
        return flag;
    }

    /**
     * @param expectedLoggedInUser
     * @return
     */
    public boolean validateLoggedInUser(String expectedLoggedInUser) {
        String actualLoggedInUserName = getLoggedInUser();
        String expectedLoggedInUserName;
        if (expectedLoggedInUser.equalsIgnoreCase("clerk")) {
            expectedLoggedInUserName = TestProperties.getInstance().getUserProperty("username.clerk");
        } else if (expectedLoggedInUser.equalsIgnoreCase("administrator")) {
            expectedLoggedInUserName = TestProperties.getInstance().getUserProperty("username.administrator");
        } else {
            expectedLoggedInUserName = testContext.get(Constants.LOGGED_IN_USER);
        }

        if (expectedLoggedInUserName.equals(actualLoggedInUserName)) {
            logger.info("Expected User Name = Actual User Name:- " + actualLoggedInUserName);
            return true;
        } else {
            logger.info("Expected User Name != Actual User Name:- ");
            logger.info("Expected User Name:- " + expectedLoggedInUserName);
            logger.info("Actual User Name:- " + actualLoggedInUserName);
            return false;
        }
    }

    /**
     * @return
     */
    public String getLoggedInUser() {
        return loginPage.getUserName();
    }

    /**
     * @param user
     */
    public void unlockLoggedInUser(String user) {
        String password;
        if (user.equalsIgnoreCase("clerk")) {
            password = TestProperties.getInstance().getUserProperty("password.clerk");
        } else if (user.equalsIgnoreCase("auditor")) {
            password = TestProperties.getInstance().getUserProperty("password.auditor");
        } else if (user.equalsIgnoreCase("administrator")) {
            password = TestProperties.getInstance().getUserProperty("password.administrator");
        } else {
            password = testContext.get(Constants.PASSWORD);
        }

        unlockUser(password);
    }

    /**
     * @param password
     */
    public void unlockUser(String password) {
        loginPage.enterPassword(password);
        loginPage.clickUnlockButton();

        logger.info("Home Page: " + homePage.getPageTitle());
        logger.info("Home Page Banner: " + homePage.getBannerText());
        Assert.assertEquals(homePage.getPageTitle(), Constants.HOME_PAGE_TITLE);
        Assert.assertEquals(homePage.getBannerText(), Constants.HOME_PAGE_BANNER);
        logger.info("Unlock Successful");
        //return homePage;
    }
}