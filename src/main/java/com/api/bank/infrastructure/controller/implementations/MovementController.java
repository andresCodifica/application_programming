package com.api.bank.infrastructure.controller.implementations;

import com.api.bank.domain.model.Movement;
import com.api.bank.domain.port.in.MovementUseCase;
import com.api.bank.infrastructure.controller.dto.GeneralResponseBuilder;
import com.api.bank.infrastructure.controller.dto.GeneralResponseDTO;
import com.api.bank.infrastructure.controller.dto.MovementRequest;
import com.api.bank.infrastructure.controller.dto.MovementResponse;
import com.api.bank.infrastructure.controller.interfaces.IMovementController;
import com.api.bank.infrastructure.controller.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MovementController implements IMovementController {

    private MovementUseCase movementUseCase;

    public MovementController(MovementUseCase movementUseCase) {
        this.movementUseCase = movementUseCase;
    }

    @Override
    public ResponseEntity<GeneralResponseDTO<MovementResponse>> getMovementByDates(String initialDate, String finalDate, String accountNumber) {
        log.info("[getMovementByDates] Start "+ initialDate + " " + finalDate + " " + accountNumber);
        GeneralResponseDTO response = new GeneralResponseBuilder()
                .setMessage(Constants.Message.MESSAGE_OK)
                .setServiceName("getMovementByDates")
                .setStatusCode(Constants.StatusCodeApi.OK)
                .setResponse(movementUseCase.getMovementByDatesAndAccount(initialDate, finalDate, accountNumber))
                .build();
        return  ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<GeneralResponseDTO<Movement>> movement(MovementRequest movementRequest) {
        log.info("[movement] Start "+ movementRequest);
        GeneralResponseDTO response = new GeneralResponseBuilder()
                .setMessage(Constants.Message.MESSAGE_OK)
                .setServiceName("movement")
                .setStatusCode(Constants.StatusCodeApi.OK)
                .setResponse(movementUseCase.createMovement(new Movement(movementRequest)))
                .build();
        return  ResponseEntity.ok().body(response);
    }
}
