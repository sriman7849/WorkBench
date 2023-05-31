package com.mastercard.gpse.processing.workflowsui;

import com.mastercard.gpse.processing.builders.AccountBuilder;
import com.mastercard.gpse.processing.constants.Constants;
import com.mastercard.gpse.processing.domain.customerserviceworkbench.Details;
import com.mastercard.gpse.processing.pages.*;
import com.mastercard.gpse.processing.pages.navigation.Navigator;
import com.mastercard.gpse.processing.utils.WaitUtility;
import com.mastercard.gpse.processing.utils.WebElementUtility;
import com.mastercard.gpse.processing.workflowsapi.WBPreRequisite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.Map;

public class FreeSearchPageWorkflow extends AbstractWorkflow {

    private static final Logger logger = (Logger) LogManager.getLogger(FreeSearchPageWorkflow.class);

    CustomerServiceWorkBenchPage cswPage;
    FreeSearchPage freeSearchPage;
    DetailsPage detailsPage;
    HomePage homePage;
    AccountBuilder accountBuilder;

    public boolean validateFreeSearchByCardHolder(Map<String, String> inputMap) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info(methodName);
        String screenShotName = new WebElementUtility().getFileNameForScreenshot(methodName);
        freeSearchPage = new FreeSearchPage();
        freeSearchPage = new Navigator().navigateToPage(FreeSearchPage.class);
        freeSearchPage.navigateToCardHolderTab();
        freeSearchPage.enterNameAndSurname(inputMap.get("NameAndSurname"));
        new CommonPage().clickFindBtn();
        boolean flag = freeSearchPage.availabilityOfDetailsLink();
        new WebElementUtility().getScreenshot(screenShotName, testContext.get(Constants.TEST_CASE_ID));
        return flag;
    }

    public boolean validateFreeSearchByCard(Map<String, String> inputMap) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info(methodName);
        String screenShotName = new WebElementUtility().getFileNameForScreenshot(methodName);
        freeSearchPage = new FreeSearchPage();
        freeSearchPage.navigateToCardTab();
        freeSearchPage.enterCardNumber(inputMap.get("CardNumber"));
        new CommonPage().clickFindBtn();
        boolean flag = freeSearchPage.availabilityOfDetailsLink();
        new WebElementUtility().getScreenshot(screenShotName, testContext.get(Constants.TEST_CASE_ID));
        return flag;
    }

    public boolean validateFreeSearchByAccountNumber(Map<String, String> inputMap) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info(methodName);
        String screenShotName = new WebElementUtility().getFileNameForScreenshot(methodName);
        freeSearchPage = new FreeSearchPage();
        freeSearchPage.navigateToAccountTab();
        freeSearchPage.enterAccountNumber(inputMap.get("AccountNumber"));
        new CommonPage().clickFindBtn();
        boolean flag = freeSearchPage.availabilityOfDetailsLink();
        new WebElementUtility().getScreenshot(screenShotName, testContext.get(Constants.TEST_CASE_ID));
        return flag;
    }

    public boolean  validateFreeSearchByAccountID() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info(methodName);
        String screenShotName = new WebElementUtility().getFileNameForScreenshot(methodName);
        accountBuilder = (AccountBuilder) WBPreRequisite.GlobalWBTestData.get(AccountBuilder.class.getSimpleName());
        String accountID = accountBuilder.getAccountID();
        new Navigator().navigateToPage(FreeSearchPage.class);
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_ONE_THOUSAND);
        freeSearchPage = new FreeSearchPage();
        freeSearchPage.navigateToAccountTab();
        freeSearchPage.enterAccountID(accountID);
        new CommonPage().clickFindBtn();
        boolean flag = freeSearchPage.availabilityOfDetailsLink();
        new WebElementUtility().getScreenshot(screenShotName, testContext.get(Constants.TEST_CASE_ID));
        return flag;
    }

    public boolean validateFreeSearchByCompany(Map<String, String> inputMap) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info(methodName);
        String screenShotName = new WebElementUtility().getFileNameForScreenshot(methodName);
        freeSearchPage = new Navigator().navigateToPage(FreeSearchPage.class);
        freeSearchPage.navigateToCompanyTab();
        freeSearchPage.enterFullCompanyName(inputMap.get("FullCompanyName"));
        new CommonPage().clickFindBtn();
        boolean flag = freeSearchPage.availabilityOfDetailsLink();
        new WebElementUtility().getScreenshot(screenShotName, testContext.get(Constants.TEST_CASE_ID));
        return flag;
    }

    public boolean validateFreeSearchByMaskedPAN(Map<String, String> inputMap) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info(methodName);
        String screenShotName = new WebElementUtility().getFileNameForScreenshot(methodName);
        freeSearchPage = new Navigator().navigateToPage(FreeSearchPage.class);
        freeSearchPage.navigateToMaskedPANTab();
        freeSearchPage.enterMaskCardNumber(inputMap.get("MaskedCardNumber"));
        new CommonPage().clickFindBtn();
        boolean flag = freeSearchPage.availabilityOfDetailsLink();
        new WebElementUtility().getScreenshot(screenShotName, testContext.get(Constants.TEST_CASE_ID));
        return flag;
    }


    public boolean validateClientDetailsByCardHolder(Details clientDetails, Map<String, String> inputMap, HomePage homePage) {
        freeSearchPage = new Navigator().navigateToPage(FreeSearchPage.class);
        freeSearchPage.navigateToCardHolderTab();
        freeSearchPage.enterNameAndSurname(inputMap.get("NameAndSurname"));
        new CommonPage().clickFindBtn();
        return freeSearchPage.availabilityOfDetailsLink();
    }

    public boolean validateClientDetailsByCard(Details clientDetails, Map<String, String> inputMap, HomePage homePage) {
        navigateToFreeSearchPage();
        new WaitUtility().threadDotSleep(3);
        freeSearchPage = new FreeSearchPage();
        freeSearchPage.navigateToCardTab();
        freeSearchPage.enterCardNumber(inputMap.get("CardNumber"));
        new CommonPage().clickFindBtn();
        return validateClient(clientDetails);
    }

    public boolean validateClientDetailsByAccount(Details clientDetails, Map<String, String> inputMap, HomePage homePage) {
        freeSearchPage = new Navigator().navigateToPage(FreeSearchPage.class);
        freeSearchPage.navigateToAccountTab();
        freeSearchPage.enterAccountNumber(inputMap.get("AccountNumber"));
        new CommonPage().clickFindBtn();
        return validateClient(clientDetails);
    }

    public boolean validateClientDetailsByCompany(Details clientDetails, Map<String, String> inputMap, HomePage homePage) {
        freeSearchPage = new Navigator().navigateToPage(FreeSearchPage.class);
        freeSearchPage.navigateToCompanyTab();
        freeSearchPage.enterFullCompanyName(inputMap.get("FullCompanyName"));
        new CommonPage().clickFindBtn();
        return freeSearchPage.availabilityOfDetailsLink();
    }

    public void navigateToFreeSearchPage() {
        homePage = pageObjectFactory.getPage(HomePage.class);
        homePage.clickCswLink();
        cswPage = pageObjectFactory.getPage(CustomerServiceWorkBenchPage.class);
        cswPage.navigateToFreeSearchPage();
    }

    private boolean validateClient(Details clientDetails) {
        detailsPage = new FreeSearchPage().clickDetailsLink();
        return detailsPage.validateClientDetails(clientDetails);
    }
}
