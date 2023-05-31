package com.mastercard.gpse.processing.enums;

import java.util.Arrays;
import java.util.Optional;

public enum CountryEnum {

    POL("POLAND"),
    USA("UNITED STATES");

    public String country;

    CountryEnum(String country) {
        this.country = country;
    }

    public String getValue() {
        return country;
    }

    public static CountryEnum getCountryEnum(String countryValue) {
        Optional<CountryEnum> enumValue =  Arrays.stream(values()).filter(coun -> coun.country.equalsIgnoreCase(countryValue))
                .findFirst();
        return enumValue.get();
    }
}
