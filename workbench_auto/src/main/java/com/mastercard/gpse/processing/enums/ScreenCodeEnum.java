package com.mastercard.gpse.processing.enums;

public enum ScreenCodeEnum {

    CLIENT_MAIN_SCREEN("CardholderTreeCardholderDetails"),
    CLIENT_ADDRESS_DETAILS("ClientAddressDetails");

    public String screenCode;

    ScreenCodeEnum(String screenCode) {
        this.screenCode = screenCode;
    }

    public String getValue() {
        return screenCode;
    }
}
