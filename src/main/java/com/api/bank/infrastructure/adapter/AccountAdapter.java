package com.api.bank.infrastructure.adapter;

import com.api.bank.domain.model.Account;
import com.api.bank.domain.port.out.AccountPersistence;
import com.api.bank.infrastructure.entity.AccountEntity;
import com.api.bank.infrastructure.repository.AccountRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("AccountPersistence")
public class AccountAdapter implements AccountPersistence {

    private final AccountRepository accountRepository;

    public AccountAdapter(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> findAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .map(accountEntity -> new Account(accountEntity));
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository
                .save(new AccountEntity(account))
                .toAccount();
    }



    @Override
    public boolean deleteAccount(String accountNumber) {
        return accountRepository
                .findByAccountNumber(accountNumber)
                .map(accountEntity -> {
                    accountRepository.deleteByAccountNumber(accountNumber);
                    return true;
                })
                .orElse(false);
    }
}
