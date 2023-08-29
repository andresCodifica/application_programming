package com.api.bank.infrastructure.controller.interfaces;

import com.api.bank.application.exception.IllegalArgumentException;
import com.api.bank.application.exception.NoContentException;
import com.api.bank.application.exception.ValueAlreadyExistsException;
import com.api.bank.domain.model.Person;
import com.api.bank.infrastructure.controller.dto.GeneralResponseDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RequestMapping(value="/api/v1/person")
public interface IPersonController {

    @GetMapping
    @ApiOperation(
            value = "Get person",
            response = String.class
    )
    ResponseEntity<GeneralResponseDTO<Person>> getPerson(@RequestParam String identification) throws NoContentException;

    @PostMapping
    @ApiOperation(
            value = "Create person",
            response = String.class
    )
    ResponseEntity<GeneralResponseDTO<Person>> createPerson(@RequestBody @Valid Person person) throws ValueAlreadyExistsException;

    @PutMapping
    @ApiOperation(
            value = "Edit person",
            response = String.class
    )
    ResponseEntity<GeneralResponseDTO<Person>> editPerson(@RequestBody @Valid Person person) throws NoContentException;

    @Transactional
    @DeleteMapping
    @ApiOperation(
            value = "Remove person",
            response = String.class
    )
    ResponseEntity<GeneralResponseDTO<Person>> removePerson(@RequestParam String identification) throws NoContentException, IllegalArgumentException;

}
