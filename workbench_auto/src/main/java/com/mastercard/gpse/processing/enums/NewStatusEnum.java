package com.mastercard.gpse.processing.enums;

import java.util.Arrays;
import java.util.Optional;

public enum NewStatusEnum {

    CARD("CARD DO NOT HONOR"),
    P1("PICKUP 04"),
    P2("PICKUP L 41"),
    P3("PICKUP S 43");

    public String newstatus;

    NewStatusEnum(String newstatus) {
        this.newstatus = newstatus;
    }

    public String getValue() {
        return newstatus;
    }

    public static NewStatusEnum getNewStatusEnum(String newstatusValue) {
        Optional<com.mastercard.gpse.processing.enums.NewStatusEnum> enumValue =  Arrays.stream(values()).filter(coun -> coun.newstatus.equalsIgnoreCase(newstatusValue))
                .findFirst();
        return enumValue.get();
    }
}


