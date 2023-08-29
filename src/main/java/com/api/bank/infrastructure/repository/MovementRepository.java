package com.api.bank.infrastructure.repository;

import com.api.bank.infrastructure.entity.AccountEntity;
import com.api.bank.infrastructure.entity.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovementRepository extends JpaRepository<MovementEntity, Integer> {

    List<MovementEntity> findByAccountEntity(AccountEntity accountEntity);
    List<MovementEntity> findByAccountEntityAndDateBetween(AccountEntity accountEntity, LocalDateTime initialDate, LocalDateTime finalDate);
    Optional<MovementEntity> findById(Integer id);
    void deleteById(Integer id);

}
