package com.api.bank.infrastructure.controller.implementations;

import com.api.bank.domain.model.Account;
import com.api.bank.domain.model.Movement;
import com.api.bank.domain.port.in.MovementUseCase;
import com.api.bank.infrastructure.controller.dto.GeneralResponseDTO;
import com.api.bank.infrastructure.controller.dto.MovementRequest;
import com.api.bank.infrastructure.controller.dto.MovementResponse;
import com.api.bank.infrastructure.controller.util.Constants;
import com.api.bank.infrastructure.controller.util.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MovementControllerTest {

    @InjectMocks
    MovementController movementController;

    @Mock
    MovementUseCase movementUseCase;


    @Test
    void getMovementByDates() {

        MovementResponse movementResponse = new MovementResponse();
        movementResponse.setName("Andres");
        movementResponse.setAccountNumber("12345678");
        movementResponse.setType("ahorros");
        movementResponse.setInitialBalance(new BigDecimal(100000));
        movementResponse.setState(true);
        movementResponse.setMovement(new BigDecimal(100));
        movementResponse.setTypeMovement("DEPOSITO");
        movementResponse.setAvailableBalance(new BigDecimal(100100));

        Mockito.when(movementUseCase.getMovementByDatesAndAccount("2021-01-01", "2021-01-01", "12345678"))
                .thenReturn(Arrays.asList(movementResponse));

        ResponseEntity<GeneralResponseDTO<MovementResponse>> response =
        movementController.getMovementByDates("2021-01-01", "2021-01-01", "12345678");
        assertEquals(Constants.StatusCodeApi.OK, response.getBody().getStatusCode());
        assertEquals(Constants.Message.MESSAGE_OK, response.getBody().getMessage());

    }

    @Test
    void movement() {
        MovementRequest movementRequest = new MovementRequest();
        Movement movement = new Movement();
        movement.setAmount(new BigDecimal(125));
        Mockito.when(movementUseCase.createMovement(new Movement(movementRequest)))
                .thenReturn(movement);


        ResponseEntity<GeneralResponseDTO<Movement>> response =
        movementController.movement(movementRequest);

        assertNotNull(response.getBody().getResponse());

    }
}