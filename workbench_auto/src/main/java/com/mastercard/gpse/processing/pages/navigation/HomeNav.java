package com.mastercard.gpse.processing.pages.navigation;

import com.mastercard.gpse.processing.enums.HomeLinksEnum;
import com.mastercard.gpse.processing.pages.BasePage;
import com.mastercard.gpse.processing.pages.HomePage;
import com.mastercard.gpse.processing.utils.WebElementUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeNav extends BasePage {

    private static final Logger logger = (Logger) LogManager.getLogger(HomePage.class);
    public static final String TAB_TITLE = "Home";

    @FindBy(xpath = "//div[contains(text(),'Customer Service Workbench')]")
    private WebElement customerServiceWorkbench;

    public HomeNav() {
        super();
    }

    public HomeNav(WebDriver driver){
        super(driver);
    }

    public WebElement getLinkElment(HomeLinksEnum elementnam) {
        return new WebElementUtility().locateElement("xpath", HomeLinksEnum.getLinkFieldElementXpath(elementnam));
    }

    /**
     * Method is used to navigate to Process Log page
     * */
    public void navigateToProcessLog()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.PROCESS_LOG),HomeLinksEnum.PROCESS_LOG.getValue());
    }

    /**
     * Method is used to navigate to W4W Message Log page
     * */
    public void navigateToW4WMessageLog()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.W4W_MESSAGE_LOG),HomeLinksEnum.W4W_MESSAGE_LOG.getValue());
    }

    /**
     * Method is used to navigate to Migration Tools page
     * */
    public void navigateToMigrationTools()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.MIGRATION_TOOLS),HomeLinksEnum.MIGRATION_TOOLS.getValue());
    }

    /**
     * Method is used to navigate to About page
     * */
    public void navigateToAbout()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.ABOUT),HomeLinksEnum.ABOUT.getValue());
    }

    /**
     * Method is used to navigate to Tagged Data Objects page
     * */
    public void navigateToTaggedDataObjects()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.TAGGED_DATA_OBJECTS),HomeLinksEnum.TAGGED_DATA_OBJECTS.getValue());
    }

    /**
     * Method is used to navigate to Officer Authentication page
     * */
    public void navigateToOfficerAuthentication()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.OFFICER_AUTHENTICATION),HomeLinksEnum.OFFICER_AUTHENTICATION.getValue());
    }

    /**
     * Method is used to navigate to Menu Configuration page
     * */
    public void navigateToMenuConfiguration()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.MENU_CONFIGURATION),HomeLinksEnum.MENU_CONFIGURATION.getValue());
    }

    /**
     * Method is used to navigate to Localisation page
     * */
    public void navigateToLocalisation()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.LOCALISATION),HomeLinksEnum.LOCALISATION.getValue());
    }

    /**
     * Method is used to navigate to Business Calendar page
     * */
    public void navigateToBusinessCalendar()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.BUSINESS_CALENDER),HomeLinksEnum.BUSINESS_CALENDER.getValue());
    }

    /**
     * Method is used to navigate to BP Configuration page
     * */
    public void navigateToBPConfiguration()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.BP_CONFIGURATION),HomeLinksEnum.BP_CONFIGURATION.getValue());
    }

    /**
     * Method is used to navigate to Stop List page
     * */
    public void navigateToStopList()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.STOP_LIST),HomeLinksEnum.STOP_LIST.getValue());
    }

    /**
     * Method is used to navigate to Risk Management Workbench page
     * */
    public void navigateToRiskManagementWorkbench()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.RISK_MANAGEMENT_WORKBENCH),HomeLinksEnum.RISK_MANAGEMENT_WORKBENCH.getValue());
    }

    /**
     * Method is used to navigate to Issuing Risk Monitoring page
     * */
    public void navigateToIssuingRiskMonitoring()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.ISSUING_RISK_MONITORING),HomeLinksEnum.ISSUING_RISK_MONITORING.getValue());
    }

    /**
     * Method is used to navigate to Issuing Risk Management Expert Tools page
     * */
    public void navigateToIssuingRiskManagementExpertTools()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.ISSUING_RISK_MANAGEMENT_EXPERT_TOOLS),HomeLinksEnum.ISSUING_RISK_MANAGEMENT_EXPERT_TOOLS.getValue());
    }

    /**
     * Method is used to navigate to Risk Case Configuration page
     * */
    public void navigateToRiskCaseConfiguration()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.RISK_CASE_CONFIGURATION),HomeLinksEnum.RISK_CASE_CONFIGURATION.getValue());
    }

    /**
     * Method is used to navigate to Product Configuration page
     * */
    public void navigateToProductConfiguration()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.PRODUCT_CONFIGURATION),HomeLinksEnum.PRODUCT_CONFIGURATION.getValue());
    }

    /**
     * Method is used to navigate to Issuing Risk Management Configuration page
     * */
    public void navigateToIssuingRiskManagementConfiguration()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.ISSUING_RISK_MANAGEMENT_CONFIGURATION),HomeLinksEnum.ISSUING_RISK_MANAGEMENT_CONFIGURATION.getValue());
    }

    /**
     * Method is used to navigate to Issuing Risk Monitoring Configuration page
     * */
    public void navigateToIssuingRiskMonitoringConfiguration()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.ISSUING_RISK_MONITORING_CONFIGURATION),HomeLinksEnum.ISSUING_RISK_MONITORING_CONFIGURATION.getValue());
    }

    /**
     * Method is used to navigate to Acquiring Risk Monitoring Configuration page
     * */
    public void navigateToAcquiringRiskMonitoringConfiguration()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.ACQUIRING_RISK_MONITORING_CONFIGURATION),HomeLinksEnum.ACQUIRING_RISK_MONITORING_CONFIGURATION.getValue());
    }

    /**
     * Method is used to navigate to Tariff Input page
     * */
    public void navigateToTariffInput()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.TRAFFIC_INPUT),HomeLinksEnum.TRAFFIC_INPUT.getValue());
    }

    /**
     * Method is used to navigate to Tariff Configuration page
     * */
    public void navigateToCustomerTariffConfiguration()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.TRAFFIC_CONFIGURATION),HomeLinksEnum.TRAFFIC_CONFIGURATION.getValue());
    }

    /**
     * Method is used to navigate to Customer Service Configuration page
     * */
    public void navigateToCustomerServiceConfiguration()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.CUSTOMER_SERVICE_CONFIGURATION),HomeLinksEnum.CUSTOMER_SERVICE_CONFIGURATION.getValue());
    }

    /**
     * Method is used to navigate to Instalment Configuration page
     * */
    public void navigateToInstalmentConfiguration()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.INSTALMENT_CONFIGURATION),HomeLinksEnum.INSTALMENT_CONFIGURATION.getValue());
    }

    /**
     * Method is used to navigate to Global Parameters page
     * */
    public void navigateToGlobalParameters()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.GLOBAL_PARAMETERS),HomeLinksEnum.GLOBAL_PARAMETERS.getValue());
    }

    /**
     * Method is used to navigate to Events, States & Classifiers page
     * */
    public void navigateToEventsStatesAndClassifiers()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.EVENTS_STATE_AND_CLASSIFIERS),HomeLinksEnum.EVENTS_STATE_AND_CLASSIFIERS.getValue());
    }

    /**
     * Method is used to navigate to Core Configuration page
     * */
    public void navigateToCoreConfiguration()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.CORE_CONFIGURATION),HomeLinksEnum.CORE_CONFIGURATION.getValue());
    }

    /**
     * Method is used to navigate to Contract Configuration page
     * */
    public void navigateToContractConfiguration()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.CONTRACT_CONFIGURATION),HomeLinksEnum.CONTRACT_CONFIGURATION.getValue());
    }

    /**
     * Method is used to navigate to Customer Service Workbench page
     * */
    public void navigateToCustomerServiceWorkbench()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.CUSTOMER_SERVICE_WORKBENCH),HomeLinksEnum.CUSTOMER_SERVICE_WORKBENCH.getValue());
    }

    /**
     * Method is used to navigate to Officers And Grants page
     * */
    public void navigateToOfficersAndGrants()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.OFFICERS_AND_GRANTS),HomeLinksEnum.OFFICERS_AND_GRANTS.getValue());
    }

    /**
     * Method is used to navigate to Currency Conversion page
     * */
    public void navigateToCurrencyConversion()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.CURRENCY_CONVERSION),HomeLinksEnum.CURRENCY_CONVERSION.getValue());
    }

    /**
     * Method is used to navigate to Institution Configuration page
     * */
    public void navigateToInstitutionConfiguration()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.INSTITUTION_CONFIGURATION),HomeLinksEnum.INSTITUTION_CONFIGURATION.getValue());
    }

    /**
     * Method is used to navigate to Tariff Configuration page
     * */
    public void navigateToTariffConfiguration()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.TARIFF_CONFIGURATION),HomeLinksEnum.TARIFF_CONFIGURATION.getValue());
    }

    /**
     * Method is used to navigate to Workbench Configuration page
     * */
    public void navigateToWorkbenchConfiguration()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.WORKBENCH_CONFIGURATION),HomeLinksEnum.WORKBENCH_CONFIGURATION.getValue());
    }

    /**
     * Method is used to navigate to Dispute Case Configuration page
     * */
    public void navigateToDisputeCaseConfiguration()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.DISPUTE_CASE_CONFIGURATION),HomeLinksEnum.DISPUTE_CASE_CONFIGURATION.getValue());
    }

    /**
     * Method is used to navigate to Dispute Management Workbench page
     * */
    public void navigateToDisputeManagementWorkbench()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.DISPUTE_MANAGEMENT_WORKBENCH),HomeLinksEnum.DISPUTE_MANAGEMENT_WORKBENCH.getValue());
    }

    /**
     * Method is used to navigate to Client Configuration page
     * */
    public void navigateToClientConfiguration()
    {
        new WebElementUtility().clickOnElement(getLinkElment(HomeLinksEnum.CLIENT_CONFIGURATION),HomeLinksEnum.CLIENT_CONFIGURATION.getValue());
    }


}
