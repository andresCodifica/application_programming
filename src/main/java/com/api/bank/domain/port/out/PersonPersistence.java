package com.api.bank.domain.port.out;

import com.api.bank.domain.model.Person;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonPersistence {
    Optional<Person> findPerson(String identification);

    Optional<Person> findByIdentificationOrPhone(String identification, String phone);
    Person savePerson(Person person);
    boolean deletePerson(String identification);
}
