package com.api.bank.domain.port.in;

import com.api.bank.application.exception.IllegalArgumentException;
import com.api.bank.application.exception.NoContentException;
import com.api.bank.application.exception.ValueAlreadyExistsException;
import com.api.bank.domain.model.Customer;
import com.api.bank.domain.model.Person;

import java.util.Optional;

public interface CustomerUseCase {
    Optional<Customer> getCustomer(Integer id);
    Customer createCustomer(Customer customer) throws NoContentException, ValueAlreadyExistsException;
    Optional<Customer> editCustomer(Customer customer) throws NoContentException;
    boolean removeCustomer(String identification) throws NoContentException, IllegalArgumentException;
}
