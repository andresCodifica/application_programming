package com.api.bank.application.service;

import com.api.bank.application.exception.IllegalArgumentException;
import com.api.bank.application.exception.NoContentException;
import com.api.bank.application.exception.ValueAlreadyExistsException;
import com.api.bank.domain.model.Person;
import com.api.bank.domain.port.in.PersonUseCase;
import com.api.bank.domain.port.out.PersonPersistence;
import com.api.bank.infrastructure.controller.util.Constants;
import com.api.bank.infrastructure.entity.PersonEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PersonUseCaseImpl implements PersonUseCase {

    private final PersonPersistence personPersistence;

    public PersonUseCaseImpl(PersonPersistence personPersistence) {
        this.personPersistence = personPersistence;
    }
    @Override
    public Optional<Person> getPerson(String identification) throws NoContentException {

        return Optional.ofNullable(personPersistence.findPerson(identification)
                .orElseThrow(() -> new NoContentException()));
    }

    @Override
    public Person createPerson(Person person) throws ValueAlreadyExistsException {
        Optional findPerson = personPersistence.findByIdentificationOrPhone(person.getIdentification(), person.getPhoneNumber());
        if(findPerson.isPresent()){
            throw new ValueAlreadyExistsException(
                    "Ya existe una persona con la identificacion o el telefono ingresado",
                    Constants.ServiceName.CREATE_PERSON
            );
        }
        return personPersistence.savePerson(person);
    }

    @Override
    public Optional<Person> editPerson(Person person) throws NoContentException {
        Optional<Person> findPerson = personPersistence.findPerson(person.getIdentification());

        if(!findPerson.isPresent()){
            throw new NoContentException();
        }

        findPerson.ifPresent(person1 -> {
            person1.setName(person.getName());
            person1.setGender(person.getGender());
            person1.setPhoneNumber(person.getPhoneNumber());
            person1.setAddress(person.getAddress());
            person1.setBirthdate(person.getBirthdate());
            personPersistence.savePerson(person1);
        });
        return findPerson;
    }

    @Override
    @Transactional
    public boolean removePerson(String identification) throws NoContentException, IllegalArgumentException {
        boolean delete = personPersistence.deletePerson(identification);
        if(!delete){
            throw new IllegalArgumentException("Person not found", Constants.ServiceName.REMOVE_PERSON);
        }
        return true;
    }
}
