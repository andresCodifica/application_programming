package com.api.bank.domain.model;

import com.api.bank.application.annotation.ValueOfEnum;
import com.api.bank.infrastructure.controller.enums.AccountEnum;
import com.api.bank.infrastructure.entity.AccountEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class Account {

    @NotNull
    @Size(min = 8, max = 20)
    private String accountNumber;

    @ValueOfEnum(enumClass = AccountEnum.class)
    private String accountType;

    @NotNull
    private BigDecimal initialBalance;

    @NotNull
    private Boolean state;


    private Customer customer;

    public Account() {
    }
    public Account (AccountEntity accountEntity){
        this.accountNumber = accountEntity.getAccountNumber();
        this.accountType = accountEntity.getAccountType();
        this.initialBalance = accountEntity.getInitialBalance();
        this.state = accountEntity.getState();
        this.customer = new Customer(accountEntity.getCustomerEntity());
    }


}