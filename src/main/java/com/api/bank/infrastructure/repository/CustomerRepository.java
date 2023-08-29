package com.api.bank.infrastructure.repository;

import com.api.bank.infrastructure.entity.CustomerEntity;
import com.api.bank.infrastructure.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    Optional<CustomerEntity> findByPerson(PersonEntity person);

    CustomerEntity save(CustomerEntity customerEntity);
    void deleteById(Integer id);

}
