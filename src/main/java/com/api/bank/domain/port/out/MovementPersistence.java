package com.api.bank.domain.port.out;

import com.api.bank.domain.model.Account;
import com.api.bank.domain.model.Movement;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovementPersistence {
    List<Movement> findMovements(Account account);

    List<Movement> findMovementsByDatesAndAccount(LocalDateTime initialDate, LocalDateTime finalDate, String accountNumber);
    Optional<Movement> findById(Integer id);
    Movement saveMovement(Movement movement);
    boolean deleteMovement(Integer id);

}
