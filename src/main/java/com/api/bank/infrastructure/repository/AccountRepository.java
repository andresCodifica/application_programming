package com.api.bank.infrastructure.repository;

import com.api.bank.infrastructure.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
    AccountEntity save(AccountEntity accountEntity);
    void deleteByAccountNumber(String accountNumber);
}
