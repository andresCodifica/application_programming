package com.api.bank.domain.model;

import com.api.bank.infrastructure.controller.enums.GenderEnum;
import com.api.bank.infrastructure.controller.util.ValueOfEnum;
import com.api.bank.infrastructure.entity.PersonEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class Person {
    private Integer idPerson;

    @NotNull
    @Size(min = 4, max = 100)
    private String name;
    @ValueOfEnum(enumClass = GenderEnum.class)
    private String gender;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    @NotNull
    @Size(min = 5, max = 40)
    private String identification;
    @NotNull
    @Size(min = 4, max = 100)
    private String address;
    @NotNull
    @Size(min = 7, max = 20)
    private String phoneNumber;

    public Person() {
    }

    public Person(PersonEntity personEntity) {
        this.idPerson = personEntity.getId();
        this.name = personEntity.getName();
        this.gender = personEntity.getGender();
        this.birthdate = personEntity.getBirthdate();
        this.identification = personEntity.getIdentification();
        this.address = personEntity.getAddress();
        this.phoneNumber = personEntity.getPhoneNumber();
    }


}