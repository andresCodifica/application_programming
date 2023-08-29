package com.api.bank.domain.port.out;

import com.api.bank.domain.model.Customer;
import com.api.bank.domain.model.Person;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CustomerPersistence {
    Optional<Customer> findById(Integer id);
    Optional<Customer> findByPerson(Person person);
    Customer saveCustomer(Customer customer);
    boolean deleteCustomer(Integer id);
}
