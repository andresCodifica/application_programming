package com.api.bank.infrastructure.controller.interfaces;

import com.api.bank.domain.model.Account;
import com.api.bank.domain.model.Customer;
import com.api.bank.infrastructure.controller.dto.AccountRequest;
import com.api.bank.infrastructure.controller.dto.GeneralResponseDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RequestMapping(value="/api/v1/account")
public interface IAccountController {

    @GetMapping
    @ApiOperation(
            value = "Get account",
            response = String.class
    )
    ResponseEntity<GeneralResponseDTO<Account>> getAccount(@RequestParam String accountNumber);

    @PostMapping
    @ApiOperation(
            value = "Create account",
            response = String.class
    )
    ResponseEntity<GeneralResponseDTO<Account>> createAccount(@RequestBody @Valid Account account);

    @PutMapping
    @ApiOperation(
            value = "Edit account",
            response = String.class
    )
    ResponseEntity<GeneralResponseDTO<Account>> editAccount(@RequestBody @Valid Account account);

    @Transactional
    @DeleteMapping
    @ApiOperation(
            value = "Delete account",
            response = String.class
    )
    ResponseEntity<GeneralResponseDTO<Account>> deleteAccount(@RequestParam String accountNumber);

}
