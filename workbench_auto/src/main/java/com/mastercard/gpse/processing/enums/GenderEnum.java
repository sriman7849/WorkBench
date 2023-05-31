package com.mastercard.gpse.processing.enums;

import java.util.Arrays;
import java.util.Optional;

public enum GenderEnum {
    F("Female"),
    M("male");

    public String gender;

    GenderEnum(String gender) {
        this.gender = gender;
    }

    public String getValue() {
        return gender;
    }

    public static GenderEnum getGenderEnum(String gender) {
        Optional<GenderEnum> enumValue =  Arrays.stream(values()).filter(gen -> gen.gender.equalsIgnoreCase(gender))
                .findFirst();
        return enumValue.get();
    }
}
