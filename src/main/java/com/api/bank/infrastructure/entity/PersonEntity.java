package com.api.bank.infrastructure.entity;

import com.api.bank.domain.model.Person;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "person")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "identification", nullable = false, length = 100)
    private String identification;

    @Column(name = "address", nullable = false, length = 150)
    private String address;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @OneToOne(mappedBy = "person")
    private CustomerEntity customerEntity;

    public PersonEntity() {
    }
    public PersonEntity(Person person) {
        this.id = person.getIdPerson();
        this.name = person.getName();
        this.gender = person.getGender();
        this.birthdate = person.getBirthdate();
        this.identification = person.getIdentification();
        this.address = person.getAddress();
        this.phoneNumber = person.getPhoneNumber();
    }

    public Person toPerson(){
        Person person = new Person();
        person.setIdPerson(this.id);
        person.setName(this.name);
        person.setGender(this.gender);
        person.setBirthdate(this.birthdate);
        person.setIdentification(this.identification);
        person.setAddress(this.address);
        person.setPhoneNumber(this.phoneNumber);
        return person;
    }
}