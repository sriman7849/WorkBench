package com.mastercard.gpse.processing.stepDefinitions.web;
import com.mastercard.gpse.processing.constants.GlobalConstants;
import com.mastercard.gpse.processing.enums.FieldsEnum;
import com.mastercard.gpse.processing.managers.Report;
import com.mastercard.gpse.processing.pages.CommonPage;
import com.mastercard.gpse.processing.pages.FieldPage;
import com.mastercard.gpse.processing.pages.OfficersGroupsPage;
import com.mastercard.gpse.processing.pages.OfficersPage;
import com.mastercard.gpse.processing.pages.navigation.Navigator;
import com.mastercard.gpse.processing.utils.SoftAssert;
import com.mastercard.gpse.processing.utils.TestContext;
import com.mastercard.gpse.processing.utils.WaitUtility;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class OfficerGroupsSteps {
    private static Logger logger = (Logger) LogManager.getLogger(ContractSearchSteps.class);
    private TestContext testContext;
    private SoftAssert softAssert;

    public OfficerGroupsSteps(TestContext context) {
        this.testContext = context;
        this.softAssert = TestContext.softAssert;
    }

    @Then("user search Officers Group by Name")
    public void userSearchOfficersGroupByName() {
        new Navigator().navigateToLinks("By Mask");
        new OfficersPage().enterName(GlobalConstants.officerGroupName);
        new OfficersPage().clickOnFindBtn();
        Report.info(logger, "searched Officers Group by Name: " + GlobalConstants.officerGroupName);
    }

    @Then("user search for {string} with {string}")
    public void userSearchForWith(String arg0, String arg1) {
        new CommonPage().lookUpWith(arg1, arg0);
    }

    @Then("user enters Password and Password Confirmation and click in save button")
    public void userEntersPasswordAndPasswordConfirmationAndClickInSaveButton() {
        new FieldPage().enterTextValue(FieldsEnum.PASSWORD.getValue(), GlobalConstants.PASSWORD);
        new FieldPage().enterTextValue(FieldsEnum.PASSWORD_CONFIRMATION.getValue(), GlobalConstants.PASSWORD);
        new CommonPage().clickOnSaveBtn();
        new CommonPage().clickOnOKBtn();
    }

    @And("user click on OK Button of Dialog Box")
    public void userClickOnOKButtonOfDialogBox() {
        new CommonPage().clickOnOKBtn();
    }

    @Then("validate Reset Failed Login Count Popup Message")
    public void validateResetFailedLoginCountPopupMessage() {
        System.out.println(new CommonPage().getPopUpDialogText());
        softAssert.assertEquals(new CommonPage().getPopUpDialogText(), "Reset Failed Login Count: Operation successfully completed", "Successfully Validated Reset Failed Login Count");
    }

    @And("validate Officers groups Details page")
    public void validateOfficersGroupsDetailsPage() {
        new CommonPage().getValueOf("Officer Type");
        softAssert.assertEquals( new CommonPage().getValueOf("Officer Type"), GlobalConstants.OFFICER_TYPE,"Successfully validate Officers type");
    }

    @And("user click on set password on {string} Screen")
    public void userClickOnSetPasswordOnScreen(String tabTitle) {
        new OfficersGroupsPage().clickOnSetPasswordBtn(tabTitle);
    }

    @And("user click on Reset Failed Login Count on {string} Screen")
    public void userClickOnResetFailedLoginCountOnScreen(String tabTitle) {
        new WaitUtility().waitForTime(WaitUtility.WAIT_TIMEOUT_IN_MILLI_SEC_THREE_THOUSAND);
        new OfficersGroupsPage().clickOnResetFailedLoginCountBtn(tabTitle);
    }

    @And("user click on Reset Failed Login Count on Details Screen")
    public void userClickOnResetFailedLoginCountOnDetailsScreen() {
        new OfficersGroupsPage().clickOnResetFailedLoginBtnOnDetailsPage();
    }

}

