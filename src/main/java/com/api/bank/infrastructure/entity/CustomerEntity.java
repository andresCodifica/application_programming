package com.api.bank.infrastructure.entity;

import com.api.bank.domain.model.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Integer id;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "state", nullable = false)
    private Boolean state = false;

    @OneToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @OneToMany(mappedBy = "customerEntity")
    private List<AccountEntity> accountEntity;

    public CustomerEntity() {
    }

    public CustomerEntity(Customer customer) {
        this.id = customer.getIdCustomer();
        this.password = customer.getPassword();
        this.state = customer.getState();
        this.person = new PersonEntity(customer.getPerson());
    }

    public Customer toCustomer() {
        Customer customer = new Customer();
        customer.setIdCustomer(this.id);
        customer.setPassword(this.password);
        customer.setState(this.state);
        customer.setPerson(this.person.toPerson());
        return customer;
    }

}