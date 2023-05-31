package com.mastercard.gpse.processing.enums;

import com.mastercard.gpse.processing.pages.navigation.HomeNav;

public enum HomeLinksEnum {

    CUSTOMER_SERVICE_WORKBENCH("Customer Service Workbench"), OFFICERS_AND_GRANTS("Officers and Grants"),
    CURRENCY_CONVERSION("Currency Conversion"),INSTITUTION_CONFIGURATION("Institution Configuration"), PRODUCT_CONFIGURATION("Product Configuration"),
    TARIFF_CONFIGURATION("Tariff Configuration"),WORKBENCH_CONFIGURATION("Workbench Configuration"),
    DISPUTE_CASE_CONFIGURATION("Dispute Case Configuration"),DISPUTE_MANAGEMENT_WORKBENCH("Dispute Management Workbench"),
    CLIENT_CONFIGURATION("Client Configuration"),CONTRACT_CONFIGURATION("Contract Configuration"),
    CORE_CONFIGURATION("Core Configuration"),EVENTS_STATE_AND_CLASSIFIERS("Events, States & Classifiers"),
    GLOBAL_PARAMETERS("Global Parameters"),
    INSTALMENT_CONFIGURATION("Instalment Configuration"),CUSTOMER_SERVICE_CONFIGURATION("Customer Service Configuration"),
    TRAFFIC_CONFIGURATION("Tariff Configuration"),TRAFFIC_INPUT("Tariff Input"),
    ACQUIRING_RISK_MONITORING_CONFIGURATION("Acquiring Risk Monitoring Configuration"),ISSUING_RISK_MANAGEMENT_CONFIGURATION("Issuing Risk Management Configuration"),
    ISSUING_RISK_MONITORING_CONFIGURATION("Issuing Risk Monitoring Configuration"),RISK_CASE_CONFIGURATION("Risk Case Configuration"),
    ISSUING_RISK_MANAGEMENT_EXPERT_TOOLS("Issuing Risk Management Expert Tools"),ISSUING_RISK_MONITORING("Issuing Risk Monitoring"),
    RISK_MANAGEMENT_WORKBENCH("Risk Management Workbench"),STOP_LIST("Stop List"),BP_CONFIGURATION("BP Configuration"),
    BUSINESS_CALENDER("Business Calendar"),LOCALISATION("Localisation"),MENU_CONFIGURATION("Menu Configuration"),
    OFFICER_AUTHENTICATION("Officer Authentication"),TAGGED_DATA_OBJECTS("Tagged Data Objects"),
    ABOUT("About"),MIGRATION_TOOLS("Migration Tools"),W4W_MESSAGE_LOG("W4W Message Log"),
    PROCESS_LOG("Process Log");

    public String field;

    public String getValue() {
        return field;
    }

    HomeLinksEnum(String elementValue) {
        this.field = elementValue;
    }

    public static String getLinkFieldElementXpath(HomeLinksEnum elementname) {
        return String.format("//div[contains(text(),'%s')]", elementname.field);
    }
}
