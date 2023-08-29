package com.api.bank.infrastructure.controller.dto;

import com.api.bank.domain.model.Account;
import com.api.bank.domain.model.Movement;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
public class MovementResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    private String name;

    private String accountNumber;

    private String type;

    private BigDecimal initialBalance;

    private boolean state;

    private BigDecimal movement;

    private String typeMovement;

    private BigDecimal availableBalance;

    public MovementResponse() {
    }


    public MovementResponse(Movement movement, Account account){
        this.date = movement.getDate();
        this.name = movement.getAccount().getCustomer().getPerson().getName();
        this.accountNumber = movement.getAccount().getAccountNumber();
        this.initialBalance = movement.getBalance();
        this.type = account.getAccountType();
        this.state = account.getState();
        this.movement = movement.getAmount();
        this.availableBalance = movement.getBalance().add(movement.getAmount());
        this.typeMovement = movement.getType();
    }
}
