package com.mastercard.gpse.processing.workflowsui;

import com.mastercard.gpse.processing.constants.Constants;
import com.mastercard.gpse.processing.pages.ContractsSearchPage;
import com.mastercard.gpse.processing.pages.navigation.Navigator;
import com.mastercard.gpse.processing.utils.WaitUtility;
import com.mastercard.gpse.processing.utils.WebElementUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.Map;

public class ContractSearchWorkFlow extends AbstractWorkflow {

    private static final Logger logger = (Logger) LogManager.getLogger(ContractSearchWorkFlow.class);

    ContractsSearchPage contractsPage = new ContractsSearchPage();
    Navigator navigator = new Navigator();

    public int validateContractSearchByBankAccountNumber(String bankAccountNumber,String bankCode) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info(methodName);
        String screenShotName = new WebElementUtility().getFileNameForScreenshot(methodName);
        new WebElementUtility().navigateBackToPreviousPage(1);
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_FIVE_THOUSAND);
         navigator.navigateToLinks("By Bank Account Number");
        contractsPage.enterBankAccountNumberAndCode(bankAccountNumber,bankCode);
        contractsPage.clickOnFind();
        int rowCount = contractsPage.getContractTableRecordsCount();
        new WebElementUtility().getScreenshot(screenShotName, testContext.get(Constants.TEST_CASE_ID));
        return rowCount;
    }
}
