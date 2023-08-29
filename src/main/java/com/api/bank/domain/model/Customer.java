package com.api.bank.domain.model;

import com.api.bank.infrastructure.entity.CustomerEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class Customer {

    private Integer idCustomer;

    private String password;


    private Boolean state;

    private Person person;
    public Customer() {
    }

    public Customer(CustomerEntity customerEntity) {
        this.idCustomer = customerEntity.getId();
        this.password = customerEntity.getPassword();
        this.state = customerEntity.getState();
        this.person = new Person(customerEntity.getPerson());
    }


}