package com.api.bank.domain.port.in;

import com.api.bank.domain.model.Account;

import java.util.Optional;

public interface AccountUseCase {
    Optional<Account> getAccount(String accountNumber);
    Account createAccount(Account account);
    Optional<Account> editAccount(Account account);
    boolean removeAccount(String accountNumber);
}
