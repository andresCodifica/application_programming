package com.api.bank.infrastructure.controller.implementations;

import com.api.bank.application.exception.IllegalArgumentException;
import com.api.bank.application.exception.NoContentException;
import com.api.bank.application.exception.ValueAlreadyExistsException;
import com.api.bank.domain.model.Person;
import com.api.bank.domain.port.in.PersonUseCase;
import com.api.bank.infrastructure.controller.dto.GeneralResponseBuilder;
import com.api.bank.infrastructure.controller.dto.GeneralResponseDTO;
import com.api.bank.infrastructure.controller.interfaces.IPersonController;
import com.api.bank.infrastructure.controller.util.Constants;
import com.api.bank.infrastructure.controller.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PersonController implements IPersonController {

    private PersonUseCase personUseCase;

    public PersonController(PersonUseCase personUseCase) {
        this.personUseCase = personUseCase;
    }
    @Override
    public ResponseEntity<GeneralResponseDTO<Person>> getPerson(String identification) throws NoContentException {
        log.info("[getPerson] Start "+ Utils.stringifyAsJson(identification));
        GeneralResponseDTO response = new GeneralResponseBuilder()
                .setMessage(Constants.Message.MESSAGE_OK)
                .setServiceName(Constants.ServiceName.GET_PERSON)
                .setStatusCode(Constants.StatusCodeApi.OK)
                .setResponse(personUseCase.getPerson(identification))
                .build();
        return  ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<GeneralResponseDTO<Person>> createPerson(Person person) throws ValueAlreadyExistsException {
        log.info("[createPerson] Start "+ Utils.stringifyAsJson(person));
        GeneralResponseDTO response = new GeneralResponseBuilder()
                .setMessage(Constants.Message.MESSAGE_OK)
                .setServiceName(Constants.ServiceName.CREATE_PERSON)
                .setStatusCode(Constants.StatusCodeApi.OK)
                .setResponse(personUseCase.createPerson(person))
                .build();
        return  ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<GeneralResponseDTO<Person>> editPerson(Person person) throws NoContentException {
        log.info("[editPerson] Start "+ Utils.stringifyAsJson(person));
        GeneralResponseDTO response = new GeneralResponseBuilder()
                .setMessage(Constants.Message.MESSAGE_OK)
                .setServiceName(Constants.ServiceName.EDIT_PERSON)
                .setStatusCode(Constants.StatusCodeApi.OK)
                .setResponse(personUseCase.editPerson(person))
                .build();
        return  ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<GeneralResponseDTO<Person>> removePerson(String identification) throws NoContentException, IllegalArgumentException {
        log.info("[removePerson] Start "+ Utils.stringifyAsJson(identification));
        GeneralResponseDTO response = new GeneralResponseBuilder()
                .setMessage(Constants.Message.MESSAGE_OK)
                .setServiceName(Constants.ServiceName.REMOVE_PERSON)
                .setStatusCode(Constants.StatusCodeApi.OK)
                .setResponse(personUseCase.removePerson(identification))
                .build();
        return  ResponseEntity.ok().body(response);
    }
}
