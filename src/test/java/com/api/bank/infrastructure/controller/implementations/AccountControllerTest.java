package com.api.bank.infrastructure.controller.implementations;

import com.api.bank.domain.model.Account;
import com.api.bank.domain.model.Customer;
import com.api.bank.domain.model.Person;
import com.api.bank.domain.port.in.AccountUseCase;
import com.api.bank.infrastructure.controller.dto.GeneralResponseDTO;
import com.api.bank.infrastructure.controller.util.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @InjectMocks
    AccountController accountController;

    @Mock
    AccountUseCase accountUseCase;

    @Test
    void getAccount() {
        Account account = new Account();

        account.setAccountNumber("12345678");
        account.setInitialBalance(new BigDecimal(100000));
        account.setState(true);
        Mockito.when(accountUseCase.getAccount("12345678"))
                .thenReturn(Optional.of(account));
        ResponseEntity<GeneralResponseDTO<Account>> response =
                accountController.getAccount("12345678");

        assertEquals(Constants.StatusCodeApi.OK, response.getBody().getStatusCode());
        assertEquals(Constants.StatusCodeApi.OK, response.getBody().getStatusCode());
        assertEquals(Constants.Message.MESSAGE_OK, response.getBody().getMessage());

    }

    @Test
    void createAccount() {
        Account account = new Account();
        Customer customer = new Customer();
        Person person = new Person();
        person.setName("Andres");
        customer.setPerson(person);
        account.setCustomer(customer);
        account.setAccountNumber("12345678");
        Mockito.when(accountUseCase.createAccount(Mockito.any(Account.class)))
                .thenReturn(account);

        ResponseEntity<GeneralResponseDTO<Account>> response =
                accountController.createAccount(account);
        assertEquals(Constants.StatusCodeApi.OK, response.getBody().getStatusCode());
        assertEquals(Constants.Message.MESSAGE_OK, response.getBody().getMessage());
        assertEquals("12345678", response.getBody().getResponse().getAccountNumber());

    }




}