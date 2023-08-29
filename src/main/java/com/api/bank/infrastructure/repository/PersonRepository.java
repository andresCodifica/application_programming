package com.api.bank.infrastructure.repository;

import com.api.bank.infrastructure.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
    Optional<PersonEntity> findByIdentification(String identification);
    Optional<PersonEntity> findByIdentificationOrPhoneNumber(String identification, String phone);

    PersonEntity save(PersonEntity personEntity);
    void deletePersonByIdentification(String identification);
}
