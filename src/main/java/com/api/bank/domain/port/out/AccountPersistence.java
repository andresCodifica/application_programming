package com.api.bank.domain.port.out;

import com.api.bank.domain.model.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountPersistence {
    Optional<Account> findAccount(String accountNumber);
    Account saveAccount(Account account);
    boolean deleteAccount(String accountNumber);
}
