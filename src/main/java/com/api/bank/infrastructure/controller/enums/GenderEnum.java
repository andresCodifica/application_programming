package com.api.bank.infrastructure.controller.enums;

public enum GenderEnum {
    MALE("M"),
    FEMALE("F");
    private final String value;

    GenderEnum(String value) {
        this.value = value;
    }



    public String getValue() {
        return value;
    }
}
