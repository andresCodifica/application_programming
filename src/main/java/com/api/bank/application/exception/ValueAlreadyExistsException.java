package com.api.bank.application.exception;

public class ValueAlreadyExistsException extends Exception {

    private static final long serialVersionUID = 1623770721255869181L;

    private String serviceName;

    public ValueAlreadyExistsException() {
        super();

    }

    public ValueAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }

    public ValueAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);

    }

    public ValueAlreadyExistsException(String message, String serviceName) {
        super(message);
        this.serviceName = serviceName;
    }

    public ValueAlreadyExistsException(String message) {
        super(message);

    }

    public ValueAlreadyExistsException(Throwable cause) {
        super(cause);

    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
