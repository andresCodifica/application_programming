package com.api.bank.application.service;

import com.api.bank.domain.model.Account;
import com.api.bank.domain.model.Customer;
import com.api.bank.domain.port.in.AccountUseCase;
import com.api.bank.domain.port.out.AccountPersistence;
import com.api.bank.domain.port.out.CustomerPersistence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class AccountUseCaseImpl implements AccountUseCase {
    private final AccountPersistence accountPersistence;
    private final CustomerPersistence customerPersistence;

    public AccountUseCaseImpl(AccountPersistence accountPersistence, CustomerPersistence customerPersistence) {
        this.accountPersistence = accountPersistence;
        this.customerPersistence = customerPersistence;
    }


    @Override
    public Optional<Account> getAccount(String accountNumber) {
        return accountPersistence.findAccount(accountNumber);
    }

    @Override
    public Account createAccount(Account account) {
        Optional.ofNullable(account.getCustomer().getIdCustomer())
                .orElseThrow(() -> new IllegalArgumentException("Customer id is required"));

        Optional<Customer> customer = Optional.ofNullable(customerPersistence.findById(account.getCustomer().getIdCustomer())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found")));

        account.setCustomer(customer.get());

        return accountPersistence.saveAccount(account);
    }

    @Override
    public Optional<Account> editAccount(Account account) {
        log.info("Account: {}", account);
        Optional<Account> accountFind =
                Optional.ofNullable(accountPersistence.findAccount(account.getAccountNumber())
                        .orElseThrow(() -> new IllegalArgumentException("Account not found")));
        Optional<Customer> customer = Optional.ofNullable(customerPersistence.findById(accountFind.get().getCustomer().getIdCustomer())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found")));

        accountFind.get().setState(account.getState());
        accountFind.get().setAccountType(account.getAccountType());
        accountFind.get().setCustomer(customer.get());
        accountFind.get().setInitialBalance(account.getInitialBalance());
        return Optional.of(accountPersistence.saveAccount(accountFind.get()));
    }

    @Override
    @Transactional
    public boolean removeAccount(String accountNumber) {
        boolean delete = accountPersistence.deleteAccount(accountNumber);
        if (!delete) {
            throw new IllegalArgumentException("Account not found");
        }
        return true;
    }
}
