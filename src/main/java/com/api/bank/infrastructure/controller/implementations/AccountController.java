package com.api.bank.infrastructure.controller.implementations;

import com.api.bank.domain.model.Account;
import com.api.bank.domain.model.Customer;
import com.api.bank.domain.port.in.AccountUseCase;
import com.api.bank.infrastructure.controller.dto.GeneralResponseBuilder;
import com.api.bank.infrastructure.controller.dto.GeneralResponseDTO;
import com.api.bank.infrastructure.controller.interfaces.IAccountController;
import com.api.bank.infrastructure.controller.util.Constants;
import com.api.bank.infrastructure.controller.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AccountController implements IAccountController {

    private AccountUseCase accountUseCase;

    public AccountController(AccountUseCase accountUseCase) {
        this.accountUseCase = accountUseCase;
    }

    @Override
    public ResponseEntity<GeneralResponseDTO<Account>> getAccount(String accountNumber) {
        log.info("[getAccount] Start "+ accountNumber);
        GeneralResponseDTO response = new GeneralResponseBuilder()
                .setMessage(Constants.Message.MESSAGE_OK)
                .setServiceName(Constants.ServiceName.GET_ACCOUNT)
                .setStatusCode(Constants.StatusCodeApi.OK)
                .setResponse(accountUseCase.getAccount(accountNumber))
                .build();
        return  ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<GeneralResponseDTO<Account>> createAccount(Account account) {
        log.info("[createAccount] Start "+ Utils.stringifyAsJson(account));
        GeneralResponseDTO response = new GeneralResponseBuilder()
                .setMessage(Constants.Message.MESSAGE_OK)
                .setServiceName(Constants.ServiceName.CREATE_ACCOUNT)
                .setStatusCode(Constants.StatusCodeApi.OK)
                .setResponse(accountUseCase.createAccount(account))
                .build();
        return  ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<GeneralResponseDTO<Account>> editAccount(Account account) {
        log.info("[editAccount] Start "+ Utils.stringifyAsJson(account));
        GeneralResponseDTO response = new GeneralResponseBuilder()
                .setMessage(Constants.Message.MESSAGE_OK)
                .setServiceName(Constants.ServiceName.EDIT_ACCOUNT)
                .setStatusCode(Constants.StatusCodeApi.OK)
                .setResponse(accountUseCase.editAccount(account))
                .build();
        return  ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<GeneralResponseDTO<Account>> deleteAccount(String accountNumber) {
        log.info("[deleteAccount] Start "+ accountNumber);
        GeneralResponseDTO response = new GeneralResponseBuilder()
                .setMessage(Constants.Message.MESSAGE_OK)
                .setServiceName(Constants.ServiceName.REMOVE_ACCOUNT)
                .setStatusCode(Constants.StatusCodeApi.OK)
                .setResponse(accountUseCase.removeAccount(accountNumber))
                .build();
        return  ResponseEntity.ok().body(response);
    }

}
