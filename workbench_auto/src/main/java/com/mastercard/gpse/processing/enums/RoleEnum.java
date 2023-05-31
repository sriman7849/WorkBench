package com.mastercard.gpse.processing.enums;

public enum RoleEnum {

    ISSUER_ADMIN("Issuer Admin"),
    LOST_AND_STOLEN_NEW("Lost and Stolen (NEW)"),
    LOST_AND_STOLEN_NEW_PCI_LESS("Lost and Stolen (NEW) PCI-Less"),
    PAY_LATER("Pay Later"),
    PAY_LATER_INSTALMENTS("Pay Later Instalments"),
    PAY_LATER_INSTALMENTS_PCI_LESS("Pay Later Instalments PCI-Less"),
    PAY_LATER_PCI_LESS("Pay Later PCI-Less"),
    PAY_NOW("Pay Now"),
    PAY_NOW_EDIT_PCI_LESS("Pay Now Edit PCI-Less"),
    PAY_NOW_PCI_LESS("Pay Now PCI-Less"),
    RISK_MANAGER_LITE("Risk Manager Lite"),
    RISK_MANAGER_LITE_PCI_LESS("Risk Manager Lite PCI-Less"),
    RISK_PAY_LATER("Risk - Pay Later"),
    RISK_PAY_LATER_INSTALMENTS("Risk - Pay Later Instalments"),
    RISK_PAY_LATER_INSTALMENTS_PCI_LESS("Risk - Pay Later Instalments PCI-Less"),
    RISK_PAY_LATER_PCI_LESS("Risk - Pay Later PCI-Less"),
    RISK_PAY_NOW("Risk - Pay Now"),
    RISK_PAY_NOW_EDIT_PCI_LESS("Risk - Pay Now Edit PCI-Less"),
    RISK_PAY_NOW_PCI_LESS("Risk - Pay Now PCI-Less"),
    TRANSACTION_MANAGEMENT("Transaction Management"),
    TRANSACTION_MANAGEMENT_PCI_LESS("Transaction Management PCI-Less");

    public String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getValue() {
        return role;
    }
}
