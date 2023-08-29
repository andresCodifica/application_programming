package com.api.bank.domain.port.in;

import com.api.bank.application.exception.IllegalArgumentException;
import com.api.bank.application.exception.NoContentException;
import com.api.bank.application.exception.ValueAlreadyExistsException;
import com.api.bank.domain.model.Person;

import java.util.Optional;

public interface PersonUseCase {
    Optional<Person> getPerson(String identification) throws NoContentException;
    Person createPerson(Person person) throws ValueAlreadyExistsException;

    Optional<Person> editPerson(Person person) throws NoContentException;
    boolean removePerson(String identification) throws NoContentException, IllegalArgumentException;

}
