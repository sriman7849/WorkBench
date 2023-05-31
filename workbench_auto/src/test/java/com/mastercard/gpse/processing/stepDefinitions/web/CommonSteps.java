package com.mastercard.gpse.processing.stepDefinitions.web;

import com.mastercard.gpse.processing.managers.Report;
import com.mastercard.gpse.processing.pages.CommonPage;
import com.mastercard.gpse.processing.utils.SoftAssert;
import com.mastercard.gpse.processing.utils.Table;
import com.mastercard.gpse.processing.utils.TestContext;
import com.mastercard.gpse.processing.utils.WaitUtility;
import com.mastercard.gpse.processing.workflowsui.AbstractWorkflow;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.List;

public class CommonSteps extends AbstractWorkflow {

    private static final Logger logger = (Logger) LogManager.getLogger(CommonSteps.class);

    private TestContext testContext;
    private SoftAssert softAssert;

    CommonPage commonPage = pageObjectFactory.getPage(CommonPage.class);

    public CommonSteps(TestContext context, SoftAssert softAssert) {
        testContext = context;
        this.softAssert = softAssert;
    }

    @Then("validate {string} tab title")
    public void validateTabTitle(String tabName) {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_TWO_THOUSAND);
        softAssert.assertEquals(tabName.toUpperCase(), commonPage.getTabTitleText(tabName));
        Report.info(logger, "Validated " + tabName + " tab title");
    }

    @And("user is able to see the record on {string} page")
    public void userIsAbleToSeeTheRecord(String tabTitle) {
        commonPage = pageObjectFactory.getPage(CommonPage.class);
        softAssert.assertTrue(commonPage.getTableRecordsCount(tabTitle) > 0, "Record Found Successfully");
    }

    @And("user is able to see the record")
    public void userIsAbleToSeeTheRecord() {
        commonPage = pageObjectFactory.getPage(CommonPage.class);
        softAssert.assertTrue(commonPage.getTableRecordsCount() > 0, "Record Found Successfully");
    }

    @Then("click on reset button")
    public void clickOnResetButton() {
        new CommonPage().clickOnResetBtn();
    }

    @Then("user validate Set Classifiers popup message")
    public void userValidateSetClassifiersPopupMessage() {
        softAssert.assertEquals(new CommonPage().getPopUpDialogText(), "Set Classifier: Successfully Completed", "Successfully validated Set Classifier PopUp message ");
    }

    @Then("validate {string} table Column {string} is Present")
    public void validateTableColumnIsPresent(String tabTitle, String colName) {
        boolean flag = false;
        Table table = new CommonPage().getTableObject(tabTitle);
        List<String> columnNames = table.getColumnNamesFromTable();
        if (columnNames.contains(colName)) {
            flag = true;
        }
        softAssert.assertTrue(flag, colName + "Column is Present");
    }

}
