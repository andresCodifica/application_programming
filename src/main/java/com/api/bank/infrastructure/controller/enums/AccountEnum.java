package com.api.bank.infrastructure.controller.enums;

public enum AccountEnum {
    AHORROS("ahorros"),
    CORRIENTE("corriente");
    private final String value;

    AccountEnum(String value) {
        this.value = value;
    }



    public String getValue() {
        return value;
    }
}
