package com.mastercard.gpse.processing.enums;

public enum StatusEnum {

    CLOSED("Closed");

    private String statusEnum;

    StatusEnum(String status) {
        this.statusEnum = status;
    }

    public String getValue() {
        return statusEnum;

    }
}
