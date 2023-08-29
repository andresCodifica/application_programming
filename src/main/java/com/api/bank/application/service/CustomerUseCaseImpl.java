package com.api.bank.application.service;

import com.api.bank.application.exception.IllegalArgumentException;
import com.api.bank.application.exception.NoContentException;
import com.api.bank.application.exception.ValueAlreadyExistsException;
import com.api.bank.domain.model.Customer;
import com.api.bank.domain.model.Person;
import com.api.bank.domain.port.in.CustomerUseCase;
import com.api.bank.domain.port.out.CustomerPersistence;
import com.api.bank.domain.port.out.PersonPersistence;
import com.api.bank.infrastructure.controller.util.Constants;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerUseCaseImpl implements CustomerUseCase {

    private final CustomerPersistence customerPersistence;

    private final PersonPersistence personPersistence;

    public CustomerUseCaseImpl(CustomerPersistence customerPersistence, PersonPersistence personPersistence) {
        this.customerPersistence = customerPersistence;
        this.personPersistence = personPersistence;
    }

    @Override
    public Optional<Customer> getCustomer(Integer id) {
        return customerPersistence.findById(id);
    }

    @Override
    public Customer createCustomer(Customer customer) throws NoContentException, ValueAlreadyExistsException {
        Optional<Person> person = personPersistence.findPerson(customer.getPerson().getIdentification());
        if(!person.isPresent()){
            throw new NoContentException();
        }
        Optional<Customer> customerFind =
                customerPersistence.findByPerson(person.get());
        if(customerFind.isPresent()){
            throw new ValueAlreadyExistsException(
                    "Persona ya registrada como cliente",
                    Constants.ServiceName.CREATE_CUSTOMER
                    );
        }

        customer.setPerson(person.get());
        customer.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));

        return customerPersistence.saveCustomer(customer);
    }

    @Override
    public Optional<Customer> editCustomer(Customer customer) throws NoContentException {
        Optional<Person> person = personPersistence.findPerson(customer.getPerson().getIdentification());
        if(!person.isPresent()){
            throw new NoContentException();
        }
        Optional<Customer> customerFind = customerPersistence.findByPerson(person.get());
        customerFind.ifPresent(customer1 -> {
            customer1.setPerson(person.get());
            customer1.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));
            customer1.setState(customer.getState());
            customerPersistence.saveCustomer(customer1);
        });
        return customerFind;
    }

    @Override
    public boolean removeCustomer(String identification) throws NoContentException, IllegalArgumentException {
        Optional<Person> person = personPersistence.findPerson(identification);
        if(!person.isPresent()){
            throw new IllegalArgumentException("Person not found", Constants.ServiceName.REMOVE_CUSTOMER);
        }
        Optional<Customer> customerFind = customerPersistence.findByPerson(person.get());

        if(!customerFind.isPresent()){
            throw new IllegalArgumentException("No existe el cliente", Constants.ServiceName.REMOVE_CUSTOMER);
        }

        return customerPersistence.deleteCustomer(customerFind.get().getIdCustomer());
    }
}
