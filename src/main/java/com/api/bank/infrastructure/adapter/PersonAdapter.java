package com.api.bank.infrastructure.adapter;

import com.api.bank.domain.model.Person;
import com.api.bank.domain.port.out.PersonPersistence;
import com.api.bank.infrastructure.entity.PersonEntity;
import com.api.bank.infrastructure.repository.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("PersonPersistence")
public class PersonAdapter implements PersonPersistence {

    private final PersonRepository personRepository;

    public PersonAdapter(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    @Override
    public Optional<Person> findPerson(String identification) {
        return personRepository.findByIdentification(identification)
                .map(personEntity -> new Person(personEntity));
    }

    @Override
    public Optional<Person> findByIdentificationOrPhone(String identification, String phone) {
        return personRepository
                .findByIdentificationOrPhoneNumber(identification, phone)
                .map(personEntity -> new Person(personEntity));
    }

    @Override
    public Person savePerson(Person person) {
        return personRepository
                .save(new PersonEntity(person))
                .toPerson();

    }


    @Override
    public boolean deletePerson(String identification) {
        return personRepository
                .findByIdentification(identification)
                .map(personEntity -> {
                    personRepository.deletePersonByIdentification(identification);
                    return true;
                })
                .orElse(false);
    }
}
