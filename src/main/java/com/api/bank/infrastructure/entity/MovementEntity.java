package com.api.bank.infrastructure.entity;

import com.api.bank.domain.model.Movement;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "movement")
public class MovementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movement_id", nullable = false)
    private Integer id;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Lob
    @Column(name = "movement_type", nullable = false)
    private String movementType;

    @Column(name = "amount", nullable = false, precision = 10)
    private BigDecimal amount;

    @Column(name = "balance", nullable = false, precision = 10)
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_number")
    private AccountEntity accountEntity;

    public MovementEntity() {
    }

    public MovementEntity(Movement movement) {
        this.id = movement.getId();
        this.date = movement.getDate();
        this.movementType = movement.getMovementType();
        this.amount = movement.getAmount();
        this.balance = movement.getBalance();
        this.accountEntity = new AccountEntity(movement.getAccount());
    }

    public Movement toMovement() {
        Movement movement = new Movement();
        movement.setId(this.id);
        movement.setDate(this.date);
        movement.setMovementType(this.movementType);
        movement.setAmount(this.amount);
        movement.setBalance(this.balance);
        movement.setAccount(this.accountEntity.toAccount());
        return movement;
    }
}