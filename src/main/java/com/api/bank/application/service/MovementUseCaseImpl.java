package com.api.bank.application.service;

import com.api.bank.domain.model.Account;
import com.api.bank.domain.model.Movement;
import com.api.bank.domain.port.in.AccountUseCase;
import com.api.bank.domain.port.in.MovementUseCase;
import com.api.bank.domain.port.out.MovementPersistence;
import com.api.bank.infrastructure.controller.dto.MovementResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovementUseCaseImpl implements MovementUseCase {
    private final MovementPersistence movementPersistence;

    private final AccountUseCase accountUseCase;

    public MovementUseCaseImpl(MovementPersistence movementPersistence, AccountUseCase accountUseCase) {
        this.movementPersistence = movementPersistence;
        this.accountUseCase = accountUseCase;
    }

    @Override
    public List<Movement> getMovementByAccount(Account account) {
        return movementPersistence.findMovements(account);
    }

    @Override
    public List<MovementResponse> getMovementByDatesAndAccount(String initialDate, String finalDate, String accountNumber) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalTime start = LocalTime.parse("00:00");
        LocalDate ld = LocalDate.parse(initialDate, formatter);

        LocalDate ldf = LocalDate.parse(finalDate, formatter);

        LocalDateTime iniDate = LocalDateTime.of(ld, LocalDateTime.of(ld,start).toLocalTime());
        LocalDateTime endDate = LocalDateTime.of(ldf, LocalDateTime.of(ld,start).toLocalTime()).plusDays(1);

        Optional<Account> account = accountUseCase.getAccount(accountNumber);
        if(account.isEmpty()){
            throw new IllegalArgumentException("No existe la cuenta");
        }

        return movementPersistence.findMovementsByDatesAndAccount(iniDate, endDate, accountNumber)
                .stream()
                .map(movement -> new MovementResponse(movement, account.get()
                )).collect(Collectors.toList());
    }

    @Override
    public Optional<Movement> getMovementById(Integer id) {
        return movementPersistence.findById(id);
    }

    @Override
    public Movement createMovement(Movement movement) {
        Optional<Account> account = accountUseCase.getAccount(movement.getAccount().getAccountNumber());
        if(account.isEmpty()){
            throw new IllegalArgumentException("No existe la cuenta");
        }

        if(movement.getType().equals("DEBIT")){
            if(account.get().getInitialBalance().compareTo(movement.getAmount()) < 0){
                throw new IllegalArgumentException("No tiene saldo suficiente");
            }
            account.get().setInitialBalance(account.get().getInitialBalance().subtract(movement.getAmount()));
            movement.setAmount(new BigDecimal("-"+movement.getAmount()));
        }else if(movement.getType().equals("DEPOSIT")){

            account.get().setInitialBalance(
                    account.get().getInitialBalance().add(movement.getAmount())
            );
            movement.setAmount(movement.getAmount());
        }else{
            throw new IllegalArgumentException("Tipo de movimiento no valido");
        }
        movement.setBalance(account.get().getInitialBalance());
        movement.setDate(movement.getDate());
        movement.setMovementType(movement.getType());
        accountUseCase.editAccount(account.get());
        movement.setAccount(account.get());
        return movementPersistence.saveMovement(movement);
    }

    @Override
    public Optional<Movement> editMovement(Movement movement) {
        return null;
    }

    @Override
    public boolean removeMovement(Integer id) {
        return movementPersistence.deleteMovement(id);
    }
}
