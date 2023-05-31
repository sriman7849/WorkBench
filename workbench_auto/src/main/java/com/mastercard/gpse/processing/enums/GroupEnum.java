package com.mastercard.gpse.processing.enums;

public enum GroupEnum {

    TOTALS("Totals"),
    GENERAL("General"),
    OTHER("Other"),
    REFERENCE_DATA("Reference Data"),
    PERSONAL_DATA("Personal Data"),
    ADDRESS("Address"),
    COMPANY_DATA("Company Data"),
    EMBOSSING_PARMS("Embossing Parms"),
    IDENTIFICATION_DATA("Identification Data"),
    GDPR_MARKETING_CONSENTS("GDPR Marketing Consents"),
    POSTAL_ADDRESS("Postal Address"),
    YES("Yes"),
    NO("No"),
    YES_NOK("0 Yes (NOK)"),
    NO_OK("0 No (OK)"),
    SEGMENT("Segment"),
    STATUS("Status"),
    CARD_DATA("Card Data"),
    TRANSACTION_DETAILS("Transaction Details");

    public String value;

    GroupEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
