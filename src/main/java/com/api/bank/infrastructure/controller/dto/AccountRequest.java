package com.api.bank.infrastructure.controller.dto;

import com.api.bank.domain.model.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest{
    private String identification;
    private Account account;
}
