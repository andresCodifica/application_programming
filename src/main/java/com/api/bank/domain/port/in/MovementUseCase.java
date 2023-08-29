package com.api.bank.domain.port.in;


import com.api.bank.domain.model.Account;
import com.api.bank.domain.model.Movement;
import com.api.bank.infrastructure.controller.dto.MovementResponse;
import com.api.bank.infrastructure.entity.AccountEntity;

import java.util.List;
import java.util.Optional;

public interface MovementUseCase {
    List<Movement> getMovementByAccount(Account account);
    List<MovementResponse> getMovementByDatesAndAccount(String initialDate, String finalDate, String accountNumber);
    Optional<Movement> getMovementById(Integer id);
    Movement createMovement(Movement movement);
    Optional<Movement> editMovement(Movement movement);
    boolean removeMovement(Integer id);


}
