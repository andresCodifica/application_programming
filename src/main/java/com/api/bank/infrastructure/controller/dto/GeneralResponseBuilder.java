package com.api.bank.infrastructure.controller.dto;

public class GeneralResponseBuilder {
    private GeneralResponseDTO generalResponseDTO;

    public GeneralResponseBuilder() {
        this.generalResponseDTO = new GeneralResponseDTO<>();
    }

    public GeneralResponseBuilder setServiceName(String serviceName){
        this.generalResponseDTO.setServiceName(serviceName);
        return this;
    }

    public GeneralResponseBuilder setStatusCode(Integer statusCode){
        this.generalResponseDTO.setStatusCode(statusCode);
        return this;
    }

    public GeneralResponseBuilder setMessage(String message){
        this.generalResponseDTO.setMessage(message);
        return this;
    }
    public GeneralResponseBuilder setResponse(Object response){
        this.generalResponseDTO.setResponse(response);
        return this;
    }

    public GeneralResponseDTO build(){
        return this.generalResponseDTO;
    }
}
