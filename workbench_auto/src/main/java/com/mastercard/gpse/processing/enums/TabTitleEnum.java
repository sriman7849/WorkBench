package com.mastercard.gpse.processing.enums;

public enum TabTitleEnum {

    INSTALMENTS("Instalments"),
    FINANCIALS("Financials"),
    FINANCIAL_DETAILS("Financial Details"),
    CHANGE_LOG("Change Log"),
    HISTORY("History"),
    DETAILS("Details"),
    CARD_DATA("Card Data"),
    GDPR("GDPR"),
    CREDIT_LIMIT_LOG("Credit Limit Log");

    public String value;

    TabTitleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
