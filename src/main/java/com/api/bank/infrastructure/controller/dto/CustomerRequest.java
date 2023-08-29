package com.api.bank.infrastructure.controller.dto;

import com.api.bank.domain.model.Customer;
import com.api.bank.domain.model.Person;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CustomerRequest {

    @NotNull
    private String identification;
    @NotNull
    @Size(min = 4, max = 20)
    private String password;

    @NotNull
    private Boolean state;

    public Customer toCustomer(){
        Customer customer = new Customer();
        Person person = new Person();
        person.setIdentification(this.identification);
        customer.setPerson(person);
        customer.setPassword(this.password);
        customer.setState(this.state);
        return customer;
    }
}
