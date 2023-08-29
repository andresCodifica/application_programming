package com.api.bank.domain.model;

import com.api.bank.infrastructure.controller.dto.MovementRequest;
import com.api.bank.infrastructure.entity.MovementEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Movement {

    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    private String movementType;
    private BigDecimal amount;
    private BigDecimal balance;
    private Account account;
    private String type;
    public Movement() {
    }
    public Movement(MovementEntity movementEntity) {
        this.type = movementEntity.getMovementType();
        this.id = movementEntity.getId();
        this.date = movementEntity.getDate();
        this.movementType = movementEntity.getMovementType();
        this.amount = movementEntity.getAmount();
        this.balance = movementEntity.getBalance();
        this.account = new Account(movementEntity.getAccountEntity());
    }

    public Movement(MovementRequest movementRequest){
        Account account = new Account();
        account.setAccountNumber(movementRequest.getAccountNumber());
        this.account = account;
        this.amount = movementRequest.getAmount();
        this.type = movementRequest.getType();
        this.date = movementRequest.getDateMovement();
    }

}