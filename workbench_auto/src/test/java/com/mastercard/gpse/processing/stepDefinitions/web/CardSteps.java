package com.mastercard.gpse.processing.stepDefinitions.web;

import com.mastercard.gpse.processing.builders.CardBuilder;
import com.mastercard.gpse.processing.enums.FieldsEnum;
import com.mastercard.gpse.processing.managers.Report;
import com.mastercard.gpse.processing.pages.CommonPage;
import com.mastercard.gpse.processing.pages.FreeSearchPage;
import com.mastercard.gpse.processing.utils.SoftAssert;
import com.mastercard.gpse.processing.utils.TestContext;
import com.mastercard.gpse.processing.workflowsapi.WBPreRequisite;
import io.cucumber.java.en.And;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class CardSteps {

    private static Logger logger = (Logger) LogManager.getLogger(CardMainScreenSteps.class);

    TestContext testContext;
    SoftAssert softAssert;

    CardBuilder cardBuilder;

    public CardSteps(TestContext testContext) {
        this.testContext = testContext;
        this.softAssert = TestContext.softAssert;
    }

    @And("user search Card by CardID")
    public void userSearchCardByCardID() {
        cardBuilder = (CardBuilder) WBPreRequisite.GlobalWBTestData.get(CardBuilder.class.getSimpleName());
        new CommonPage().enterTextIn(FieldsEnum.CONTRACT_ID.getValue(), cardBuilder.getCardID());
        new CommonPage().clickFindBtn();
        Report.info(logger, "User search cardDetails by accountNumber");
    }
}
