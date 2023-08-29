package com.api.bank.infrastructure.adapter;

import com.api.bank.domain.model.Account;
import com.api.bank.domain.model.Movement;
import com.api.bank.domain.port.in.AccountUseCase;
import com.api.bank.domain.port.out.MovementPersistence;
import com.api.bank.infrastructure.entity.AccountEntity;
import com.api.bank.infrastructure.entity.MovementEntity;
import com.api.bank.infrastructure.repository.MovementRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("MovementPersistence")
public class MovementAdapter implements MovementPersistence {

    private final MovementRepository movementRepository;
    private final AccountUseCase accountUseCase;
    public MovementAdapter(MovementRepository movementRepository, AccountUseCase accountUseCase) {
        this.movementRepository = movementRepository;
        this.accountUseCase = accountUseCase;
    }

    @Override
    public List<Movement> findMovements(Account account) {
        return movementRepository.findByAccountEntity(new AccountEntity(account))
                .stream()
                .map(movementEntity -> new Movement(movementEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Movement> findMovementsByDatesAndAccount(LocalDateTime initialDate, LocalDateTime finalDate, String accountNumber) {
        Optional<Account> account = accountUseCase.getAccount(accountNumber);
        if(account.isEmpty()){
            throw new IllegalArgumentException("No existe la cuenta");
        }
        return movementRepository.findByAccountEntityAndDateBetween(new AccountEntity(account.get()), initialDate, finalDate)
                .stream()
                .map(movementEntity -> new Movement(movementEntity))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Movement> findById(Integer id) {
        return movementRepository.findById(id)
                .map(movementEntity -> new Movement(movementEntity));
    }

    @Override
    public Movement saveMovement(Movement movement) {
        return movementRepository.save(new MovementEntity(movement))
                .toMovement();
    }


    @Override
    public boolean deleteMovement(Integer id) {
        return movementRepository.findById(id)
                .map(movementEntity -> {
                    movementRepository.deleteById(movementEntity.getId());
                    return true;
                })
                .orElse(false);
    }
}
