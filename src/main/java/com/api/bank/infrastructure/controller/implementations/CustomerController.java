package com.api.bank.infrastructure.controller.implementations;

import com.api.bank.application.exception.IllegalArgumentException;
import com.api.bank.application.exception.NoContentException;
import com.api.bank.application.exception.ValueAlreadyExistsException;
import com.api.bank.domain.model.Customer;
import com.api.bank.domain.port.in.CustomerUseCase;
import com.api.bank.infrastructure.controller.dto.CustomerRequest;
import com.api.bank.infrastructure.controller.dto.GeneralResponseBuilder;
import com.api.bank.infrastructure.controller.dto.GeneralResponseDTO;
import com.api.bank.infrastructure.controller.interfaces.ICustomerController;
import com.api.bank.infrastructure.controller.util.Constants;
import com.api.bank.infrastructure.controller.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CustomerController implements ICustomerController {

    private CustomerUseCase customerUseCase;

    public CustomerController(CustomerUseCase customerUseCase) {
        this.customerUseCase = customerUseCase;
    }

    @Override
    public ResponseEntity<GeneralResponseDTO<Customer>> getCustomer(Integer id) {
        log.info("[getCustomer] Start "+ id);
        GeneralResponseDTO response = new GeneralResponseBuilder()
                .setMessage(Constants.Message.MESSAGE_OK)
                .setServiceName(Constants.ServiceName.GET_CUSTOMER)
                .setStatusCode(Constants.StatusCodeApi.OK)
                .setResponse(customerUseCase.getCustomer(id))
                .build();
        return  ResponseEntity.ok().body(response);
    }


    @Override
    public ResponseEntity<GeneralResponseDTO<Customer>> createCustomer(CustomerRequest customer) throws NoContentException, ValueAlreadyExistsException {
        log.info("[createCustomer] Start "+ Utils.stringifyAsJson(customer));
        GeneralResponseDTO response = new GeneralResponseBuilder()
                .setMessage(Constants.Message.MESSAGE_OK)
                .setServiceName(Constants.ServiceName.CREATE_CUSTOMER)
                .setStatusCode(Constants.StatusCodeApi.OK)
                .setResponse(customerUseCase.createCustomer(customer.toCustomer()))
                .build();
        return  ResponseEntity.ok().body(response);

    }

    @Override
    public ResponseEntity<GeneralResponseDTO<Customer>> editCustomer(CustomerRequest customer) throws NoContentException {
        log.info("[editCustomer] Start "+ Utils.stringifyAsJson(customer));
        GeneralResponseDTO response = new GeneralResponseBuilder()
                .setMessage(Constants.Message.MESSAGE_OK)
                .setServiceName(Constants.ServiceName.EDIT_CUSTOMER)
                .setStatusCode(Constants.StatusCodeApi.OK)
                .setResponse(customerUseCase.editCustomer(customer.toCustomer()))
                .build();
        return  ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<GeneralResponseDTO<Boolean>> removeCustomer(String identification) throws NoContentException, IllegalArgumentException {
        log.info("[removeCustomer] Start "+ identification);
        GeneralResponseDTO response = new GeneralResponseBuilder()
                .setMessage(Constants.Message.MESSAGE_OK)
                .setServiceName(Constants.ServiceName.REMOVE_CUSTOMER)
                .setStatusCode(Constants.StatusCodeApi.OK)
                .setResponse(customerUseCase.removeCustomer(identification))
                .build();
        return  ResponseEntity.ok().body(response);
    }
}
