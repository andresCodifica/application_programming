package com.api.bank.infrastructure.controller.interfaces;


import com.api.bank.domain.model.Customer;
import com.api.bank.domain.model.Movement;
import com.api.bank.infrastructure.controller.dto.GeneralResponseDTO;
import com.api.bank.infrastructure.controller.dto.MovementRequest;
import com.api.bank.infrastructure.controller.dto.MovementResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value="/api/v1/movement")
public interface IMovementController {

    @GetMapping
    @ApiOperation(
            value = "Get movement by dates",
            response = String.class
    )
    ResponseEntity<GeneralResponseDTO<MovementResponse>> getMovementByDates(
            @RequestParam String initialDate,
            @RequestParam String finalDate,
            @RequestParam String accountNumber);

    @PostMapping
    @ApiOperation(
            value = "Deposit - Withdrawal",
            response = String.class
    )
    ResponseEntity<GeneralResponseDTO<Movement>> movement(@RequestBody @Valid MovementRequest movementRequest);


}
