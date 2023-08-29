package com.api.bank.infrastructure.controller.interfaces;

import com.api.bank.application.exception.IllegalArgumentException;
import com.api.bank.application.exception.NoContentException;
import com.api.bank.application.exception.ValueAlreadyExistsException;
import com.api.bank.domain.model.Customer;
import com.api.bank.infrastructure.controller.dto.CustomerRequest;
import com.api.bank.infrastructure.controller.dto.GeneralResponseDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value="/api/v1/customer")
public interface ICustomerController {

    @GetMapping
    @ApiOperation(
            value = "Get customer",
            response = String.class
    )
    ResponseEntity<GeneralResponseDTO<Customer>> getCustomer(@RequestParam Integer id);

    @PostMapping
    @ApiOperation(
            value = "Create customer",
            response = String.class
    )
    ResponseEntity<GeneralResponseDTO<Customer>> createCustomer(@RequestBody @Valid CustomerRequest customer) throws NoContentException, ValueAlreadyExistsException;

    @PutMapping
    @ApiOperation(
            value = "Edit customer",
            response = String.class
    )
    ResponseEntity<GeneralResponseDTO<Customer>> editCustomer(@RequestBody @Valid CustomerRequest customer) throws NoContentException;

    @DeleteMapping
    @ApiOperation(
            value = "Remove customer",
            response = String.class
    )
    ResponseEntity<GeneralResponseDTO<Boolean>> removeCustomer(String identification) throws NoContentException, IllegalArgumentException;



}
