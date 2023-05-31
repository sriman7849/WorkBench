package com.mastercard.gpse.processing.constants;

public class Constants {
    public static final String USER_DIR = System.getProperty("user.dir");
    public static final String CONFIG_DIR_PATH = "src/main/resources/config";
    public static final String RUNTIME_ENVIRONMENT = "stage";
    public static final String SUITE_NAME = System.getProperty("suite");
    public static final String PATH_SEPARATOR = "/";
    public static final String BUILD = System.getProperty("build");
    public static final String UPLOAD_SCREENSHOTS = System.getProperty("uploadScreenshots");
    public static final String UPLOAD_RESULTS = System.getProperty("uploadResults");

    public static final String API_DATA_FILE = USER_DIR + "\\src\\main\\resources\\config\\stage\\data\\apidata\\";

    //Constants for Selenium
    public static final String PATH_TO_CHROME_BINARY = "C:/driver/chromedriver.exe";
    public static final String PATH_TO_FIREFOX_BINARY = "C:/driver/geckodriver.exe";

    //Role PCI
    public static final String ROLE_PCI_LESS="PCI_LESS";
    public static final String LOGGED_IN_USER = "Logged_In_User";
    public static final String PASSWORD ="Password";
    public static final String OTPKEY = "Otpkey";

    //Application Constants
    public static final String LOGIN_PAGE_TITLE = "Web Workbenches";
    public static final String LOGIN_PAGE_BANNER= "Web Workbenches";
    public static final String LOGIN_ERROR_MESSAGE = "Invalid login or password";

    public static final String HOME_PAGE_TITLE = "Web Workbenches";
    //public static final String HOME_PAGE_TITLE = "MPTS - WAY4 Web 3.0";
    public static final String OLD_HOME_PAGE_TITLE = "Openway - Web Client";

    public static final String HOME_PAGE_BANNER= "MPTS - WAY4 WEB 3.0";
    public static final String DIR_PATH_FOR_SCREENSHOTS = "";
    public static final String RALLY_NOTES = "Executed Through Automation";
    public static final String TEST_CASE_ID = "testCaseID";
    public static final String TEST_CASE_NAME = "testCaseName";

    //Constants for RiskCaseConfiguartion
    public static final String RISK_CASE_CONFIGURATION_DOMAINS_PAGE = "Domains";
    public static final String RISK_CASE_CONFIGURATION_USERGROUPS_PAGE = "User Groups";
    public static final String RISK_CASE_CONFIGURATION_USERS_PAGE = "Users";
    public static final String RISK_CASE_CONFIGURATION_ROLES_PAGE = "Roles";
    public static final String RISK_CASE_CONFIGURATION_DETAILS_PAGE = "Details";
    public static final String RISK_CASE_CONFIGURATION_USERS_USERGROUPS_ALL_PAGE = "All";
    public static final String RISK_CASE_CONFIGURATION_USERS_USERGROUPS_GET_ALL_PAGE = "Get All";
    public static final String RISK_CASE_CONFIGURATION_USERS_USERGROUPS_GET_ACTIVE_PAGE = "Get Active";
    public static final String RISK_CASE_CONFIGURATION_USERS_USERGROUPS_GET_INACTIVE_PAGE = "Get Inactive";
    public static final String RISK_CASE_CONFIGURATION_USERS_USERGROUPS_GET_TEMPLATE_USERS_PAGE = "Get Template Users";
    public static final String RISK_CASE_CONFIGURATION_USERS_GROUP_ROLES_PAGE = "Group Roles";
    public static final String RISK_CASE_CONFIGURATION_USERS_OWN_ROLES_PAGE = "Own Roles";
    public static final String RISK_CASE_CONFIGURATION_USERS_RESTRICTIONS_PAGE = "Restrictions";




    //Constants for CardMainScreen
    public static final String Card_MAIN_SCREEN_CARD_DATA_TAB = "Card Data";
    public static final String Card_MAIN_SCREEN_FINANCIALS_TAB = "Financials";
    public static final String Card_MAIN_SCREEN_TRANSACTIONS_TAB = "Transactions";
    public static final String Card_MAIN_SCREEN_BILLING_HISTORY_TAB = "BILLING HISTORY";
    public static final String Card_MAIN_SCREEN_STATEMENTS_TAB = "Statements";
    public static final String Card_MAIN_SCREEN_SERVICES_TAB = "Services";
    public static final String Card_MAIN_SCREEN_RISK_CONTROLS_TAB = "Risk Controls";
    public static final String Card_MAIN_SCREEN_INSTALMENTS_TAB = "INSTALMENTS";
    public static final String Card_MAIN_SCREEN_ADDRESSES_TAB = "Addresses";
    public static final String Card_MAIN_SCREEN_OWN_ADDRESSES_TAB = "Own Addresses";
    public static final String Card_MAIN_SCREEN_CLASSIFIERS_TAB = "Classifiers";
    public static final String Card_MAIN_SCREEN_CONTRACT_PARAMETERS_TAB = "Contract Parameters";
    public static final String Card_MAIN_SCREEN_NOTIFICATIONS_TAB = "Notifications";
    public static final String Card_MAIN_SCREEN_CASES_TAB = "Cases";
    public static final String Card_MAIN_SCREEN_APPLICATION_INFO_TAB = "Application Info";
    public static final String Card_MAIN_SCREEN_ADDITIONAL_INFO_TAB = "Additional Info";
    public static final String Card_MAIN_SCREEN_HISTORY_TAB = "History";
    public static final String Card_MAIN_SCREEN_CHANGE_LOG_TAB = "Change Log";
    public static final String Card_MAIN_SCREEN_BALANCES_TAB = "Balances";
    public static final String Card_MAIN_SCREEN_LINKED_CLIENTS_TAB = "Linked Clients";
    public static final String Card_MAIN_SCREEN_PAYEES_TAB = "Payees";
    public static final String Card_MAIN_SCREEN_PLANNED_STATUS_CHANGES_TAB = "Planned Status Changes";
    public static final String Card_MAIN_SCREEN_BILLING_HISTORY_INTEREST_CALCULATION_TAB = "INTEREST CALCULATION";

    //Officers And Grants
    public static final String OFFICERS_IN_GROUP_TAB = "Officers In Group";
    public static final String OFFICER_GROUPS_TAB = "Officer Groups";
    public static final String USED_BY_OFFICER_GROUPS_TAB = "Used By Officer Groups";
    public static final String CUSTOM_PROFILES_ACCESS_TAB = "CUSTOM PROFILES ACCESS";
    public static final String OFFICERS_TAB = "Officers";
    public static final String OFFICERS_CUSTOM_PROFILES_TAB = "Custom Profiles Access";
    public static final String DETAILS_TAB = "Details";
    //Contract Main Screen
    public static final String Contract_MAIN_SCREEN_STATUS = "Account OK";

    //Dispute Case Configuration
    public static final String Dispute_Case_Configuration_DOMAINS = "Domains";
    public static final String Dispute_Case_Configuration_USERS = "Users";
    public static final String Dispute_Case_Configuration_ROLE = "Role";

    //Documents - Authorization Details
    public static final String AUTHORIZATION_DETAILS_TAB = "Authorisation Details";
    public static final String BLOCK_LINKS_TAB = "Blocked Funds";
    public static final String DETAILS = "Details";
    public static final String TRANSACTION_CONDITIONS = "Transaction Conditions";
    public static final String DOCUMENT_CHAIN = "Document Chain";
    public static final String TAGGED_DATA = "Tagged Data";

    //Documents - Financial Details
    public static final String FINANCIAL_DETAILS_TAB = "Financial Details";

    //Issuing Risk Monitoring
    public static final String Issuing_Risk_Monitoring_Document_Page = "By Document";
    public static final String Issuing_Risk_Monitoring_By_Document_Blocked_Funds_Page = "Blocked Funds";
    public static final String Issuing_Risk_Monitoring_By_Risk_Rule_Page = "By Risk Rule";
    public static final String Issuing_Risk_Monitoring_By_Contract_Page = "By Contract";

    //Transaction Management
    public static final String Transaction_Management_Document_Page = "DOCUMENTS";
    public static final String Transaction_Management_Document_Authorisation_Details_Page = "Authorisation Details";
    public static final String Transaction_Management_Document_Document_Chain_Page = "Document Chain" ;
    public static final String Transaction_Management_Document_Document_Chain_Details_Page = "Details";
    public static final String Transaction_Management_Document_Tagged_Data_Page = "Tagged Data";
    public static final String Transaction_Management_Document_Financial_Details_Page = "Financial Details";
    public static final String Transaction_Management_Document_Transaction_Conditions_Page = "Transaction Conditions";
    public static final String Transaction_Management_Document_Document_Details_Page = "Document Details";

    private Constants(){
    }

}
