package com.api.bank.infrastructure.controller.dto;

public class GeneralResponseDTO<T> {
    public static final String SERVICE_NAME_ATTRIBUTE = "serviceName";
    private String serviceName;
    private Integer statusCode;
    private String message;
    private T response;

    public GeneralResponseDTO() {
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}