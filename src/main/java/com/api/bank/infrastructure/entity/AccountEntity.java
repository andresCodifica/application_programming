package com.api.bank.infrastructure.entity;

import com.api.bank.domain.model.Account;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "account")
public class AccountEntity {
    @Id
    @Column(name = "account_number", nullable = false, length = 100)
    private String accountNumber;

    @Lob
    @Column(name = "account_type", nullable = false)
    private String accountType;

    @Column(name = "initial_balance", nullable = false, precision = 10)
    private BigDecimal initialBalance;

    @Column(name = "state", nullable = false)
    private Boolean state = false;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

    @OneToMany(mappedBy = "accountEntity")
    private List<MovementEntity> movementEntities;

    public AccountEntity() {
    }

    public AccountEntity(Account account) {
        this.accountNumber = account.getAccountNumber();
        this.accountType = account.getAccountType();
        this.initialBalance = account.getInitialBalance();
        this.customerEntity = new CustomerEntity(account.getCustomer());
        this.state = account.getState();
    }

    public Account toAccount() {
        Account account = new Account();
        account.setAccountNumber(this.accountNumber);
        account.setAccountType(this.accountType);
        account.setInitialBalance(this.initialBalance);
        account.setState(this.state);
        account.setCustomer(this.customerEntity.toCustomer());
        return account;
    }
}