package com.mastercard.gpse.processing.stepDefinitions.web;

import com.mastercard.gpse.processing.builders.CardBuilder;
import com.mastercard.gpse.processing.builders.ClientBuilder;
import com.mastercard.gpse.processing.domain.customerserviceworkbench.Details;
import com.mastercard.gpse.processing.managers.Report;
import com.mastercard.gpse.processing.pages.CommonPage;
import com.mastercard.gpse.processing.pages.FreeSearchPage;
import com.mastercard.gpse.processing.utils.ExcelUtils;
import com.mastercard.gpse.processing.utils.SoftAssert;
import com.mastercard.gpse.processing.utils.TestContext;
import com.mastercard.gpse.processing.workflowsapi.WBPreRequisite;
import com.mastercard.gpse.processing.workflowsui.FreeSearchPageWorkflow;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.Assert;

import java.util.HashMap;


public class FreeSearchSteps {

    private static final Logger logger = (Logger) LogManager.getLogger(FreeSearchSteps.class);

    ClientBuilder clientBuilder=new ClientBuilder();
    CardBuilder cardBuilder;
    FreeSearchPageWorkflow freeSearchPageWorkflow;

    HashMap<String, String> dataMap = new HashMap<>();
    HashMap<String, String> inputMap = new HashMap<>();
    Details expectedClientDetails;

    private TestContext testContext;
    private SoftAssert softAssert;

    public FreeSearchSteps(TestContext context) {
        testContext = context;
        this.softAssert = TestContext.softAssert;
    }

    @Given("Free Search with positive data")
    public void validateFreeSearch() throws InterruptedException {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

        String testCaseID = "TC2570403";
        String testCaseName = "FreeSearch_SuccessfulSearch";
        /*configTest.setTestCaseContext(testCaseID,testCaseName);
        configTest.logTestCaseContext();

        loginWorkflow.loginToCSWAsClerkUser();*/
        readTestData();
        boolean result;
        freeSearchPageWorkflow = new FreeSearchPageWorkflow();
        result = freeSearchPageWorkflow.validateFreeSearchByCardHolder(inputMap);
        Assert.assertTrue(result, "Record was Not Found. Expected was record should be found");
        result = freeSearchPageWorkflow.validateFreeSearchByAccountNumber(inputMap);
        Assert.assertTrue(result, "Record was Not Found. Expected was record should be found");
        result = freeSearchPageWorkflow.validateFreeSearchByCompany(inputMap);
        Assert.assertTrue(result, "Record was Not Found. Expected was record should be found");

    }

    /*@Test(priority = 1, groups = {"all", "CSW","TRV"})
    @Severity(SeverityLevel.NORMAL)
    @Description("Free Search with Pattern String")
    public void validateFreeSearchWithCardHolderPatternString() throws InterruptedException {
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

        String testCaseID = "TC2570403";
        String testCaseName = "FreeSearch_SuccessfulSearch";
        configTest.setTestCaseContext(testCaseID,testCaseName);
        configTest.logTestCaseContext();

        loginWorkflow.loginToCSWAsClerkUser();
        boolean result;
        HashMap<String,String> inputMap = new HashMap<>();
        inputMap.put("NameAndSurname","[Ahuja]%");
        result = freeSearchPageWorkflow.validateFreeSearchByCardHolder(inputMap);

        Assert.assertTrue(result,"Record was Not Found. Expected was record should be found");

    }

    //@Test(priority = 1)
    public void validateClientDetailsByCardHolder() throws InterruptedException {

      logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
      homePage = loginWorkflow.loginToCSWAsClerkUser();
      boolean result = freeSearchPageWorkflow
              .validateClientDetailsByCardHolder(expectedClientDetails,inputMap,homePage);
      Assert.assertTrue(result,"Mismatch in Expected and Actual Field(s)");
  }

    //@Test(priority = 2)
    public void validateClientDetailsByCard() throws InterruptedException {

        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
        homePage = loginWorkflow.loginToCSWAsClerkUser();
        boolean result = freeSearchPageWorkflow.validateClientDetailsByCard(expectedClientDetails,inputMap,homePage);
        Assert.assertTrue(result,"Mismatch in Expected and Actual Field(s)");
    }

    //@Test(priority = 3)
    public void validateClientDetailsByAccount() throws InterruptedException {

        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
        homePage = loginWorkflow.loginToCSWAsClerkUser();
        boolean result = freeSearchPageWorkflow
                .validateClientDetailsByAccount(expectedClientDetails,inputMap,homePage);
        Assert.assertTrue(result,"Mismatch in Expected and Actual Field(s)");


    }

    //@Test(priority = 4)
    public void validateClientDetailsByCompany() throws InterruptedException {

        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
        homePage = loginWorkflow.loginToCSWAsClerkUser();
        boolean result = freeSearchPageWorkflow
                .validateClientDetailsByCompany(expectedClientDetails,inputMap,homePage);
        Assert.assertTrue(result,"Mismatch in Expected and Actual Field(s)");
    }*/

    public void readTestData() {
        HashMap<String, HashMap<String, String>> entireTestData = new HashMap<String, HashMap<String, String>>();
        ExcelUtils excelUtils = new ExcelUtils();

        entireTestData = excelUtils.readTestData("ClientContractDetails.xlsx", "Client");
        dataMap = entireTestData.get("1");
        expectedClientDetails = new Details().getDetails(dataMap);

        entireTestData = excelUtils.readTestData("ClientContractSearchCriteria.xlsx", "FreeSearch");
        inputMap = entireTestData.get("1");

        logger.info(dataMap.toString());
        logger.info(inputMap.toString());
    }

    @And("validate search in Free Search by Account ID")
    public void userSearchInFreeSearchByAccountID() {
        softAssert.assertTrue(new FreeSearchPageWorkflow().validateFreeSearchByAccountID(), "Expected record is present");
    }

    @Then("user Free Search by Company name")
    public void userSearchByCompanyName() {
        clientBuilder = (ClientBuilder) WBPreRequisite.GlobalWBTestData.get(ClientBuilder.class.getSimpleName());
        inputMap.put("FullCompanyName", clientBuilder.getClientCompanyData().getCompanyName());
        softAssert.assertTrue(new FreeSearchPageWorkflow().validateFreeSearchByCompany(inputMap), "Record Found");
    }

    @Then("user Free Search by Card Holder name")
    public void userFreeSearchByCardHolderName() {
        cardBuilder = (CardBuilder) WBPreRequisite.GlobalWBTestData.get(CardBuilder.class.getSimpleName());
        inputMap.put("NameAndSurname",// cardBuilder.getCard().getEmbossedData().getEmbossedFirstName() + " " +
                cardBuilder.getCard().getEmbossedData().getEmbossedLastName());
        softAssert.assertTrue(new FreeSearchPageWorkflow().validateFreeSearchByCardHolder(inputMap), "Record Found");
    }

    @Then("user Free Search by Card Holder name using substring")
    public void userFreeSearchByCardHolderNameUsingSubstring() {
        cardBuilder = (CardBuilder) WBPreRequisite.GlobalWBTestData.get(CardBuilder.class.getSimpleName());
        boolean result;
        String fullName = cardBuilder.getCard().getEmbossedData().getEmbossedFirstName() + " " + cardBuilder.getCard().getEmbossedData().getEmbossedLastName();
        String nameSubstring = fullName.substring(fullName.length() / 4, fullName.length() * 3 / 4);
        nameSubstring = fullName.replaceAll(nameSubstring, "%");
        inputMap.put("NameAndSurname", nameSubstring);
        softAssert.assertTrue(new FreeSearchPageWorkflow().validateFreeSearchByCardHolder(inputMap), "Record Found");
    }

    @Then("user Free Search by Masked Card Number")
    public void userFreeSearchByMaskedCardNumber() {
        cardBuilder = (CardBuilder) WBPreRequisite.GlobalWBTestData.get(CardBuilder.class.getSimpleName());
        String maskedPan="540897%7687";
        //inputMap.put("MaskedCardNumber", cardBuilder.getPlasticsList().getCardPAN().replaceAll("_+", "%"));
        inputMap.put("MaskedCardNumber", maskedPan);
        Assert.assertTrue(new FreeSearchPageWorkflow().validateFreeSearchByMaskedPAN(inputMap), "Record Found");
    }


    @When("user search cardDetails by accountNumber")
    public void userSearchCardDetailsByAccountNumber() {
        cardBuilder = (CardBuilder) WBPreRequisite.GlobalWBTestData.get(CardBuilder.class.getSimpleName());
        new FreeSearchPage().navigateToAccountTab();
        new FreeSearchPage().enterAccountNumber(cardBuilder.getAccountContractNumber());
        new CommonPage().clickFindBtn();
        Report.info(logger, "User search cardDetails by accountNumber");
    }
}
