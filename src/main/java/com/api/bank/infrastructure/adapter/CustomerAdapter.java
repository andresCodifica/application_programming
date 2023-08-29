package com.api.bank.infrastructure.adapter;

import com.api.bank.domain.model.Customer;
import com.api.bank.domain.model.Person;
import com.api.bank.domain.port.out.CustomerPersistence;
import com.api.bank.infrastructure.entity.CustomerEntity;
import com.api.bank.infrastructure.entity.PersonEntity;
import com.api.bank.infrastructure.repository.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("CustomerPersistence")
public class CustomerAdapter implements CustomerPersistence {
    private final CustomerRepository customerRepository;

    public CustomerAdapter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Optional<Customer> findById(Integer id) {
        return customerRepository.findById(id)
                .map(customerEntity -> new Customer(customerEntity));
    }

    @Override
    public Optional<Customer> findByPerson(Person person) {
        return customerRepository.findByPerson(new PersonEntity(person))
                .map(customerEntity -> new Customer(customerEntity));
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository
                .save(new CustomerEntity(customer))
                .toCustomer();
    }


    @Override
    public boolean deleteCustomer(Integer id) {
        return customerRepository
                .findById(id)
                .map(customerEntity -> {
                    customerRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}
