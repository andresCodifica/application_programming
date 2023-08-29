package com.api.bank.application.config;

import com.api.bank.application.exception.NoContentException;
import com.api.bank.application.exception.ValueAlreadyExistsException;
import com.api.bank.infrastructure.controller.dto.GeneralResponseDTO;
import com.api.bank.infrastructure.controller.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
@RestController
public class ServerApiErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NoContentException.class)
    @ResponseBody
    public ResponseEntity<GeneralResponseDTO<Void>> noContentErrorHandler(final HttpServletRequest httpServletRequest,
                                                                          final HttpServletResponse httpServletResponse, NoContentException noContentException) {
        GeneralResponseDTO<Void> response = new GeneralResponseDTO<>();
        response.setStatusCode(Constants.StatusCodeApi.ERROR_NO_CONTENT);
        response.setMessage(noContentException.getMessage());
        response.setServiceName(noContentException.getServiceName());
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(response);

    }
    @ExceptionHandler(ValueAlreadyExistsException.class)
    @ResponseBody
    public ResponseEntity<GeneralResponseDTO<Void>> valueAlreadyExistsErrorHandler(final HttpServletRequest httpServletRequest,
                                                                                   final HttpServletResponse httpServletResponse, ValueAlreadyExistsException valueAlreadyExistsException) {
        GeneralResponseDTO<Void> response = new GeneralResponseDTO<>();
        response.setStatusCode(Constants.StatusCodeApi.ERROR_GENERIC);
        response.setMessage(valueAlreadyExistsException.getMessage());
        response.setServiceName(valueAlreadyExistsException.getServiceName());
        return ResponseEntity.badRequest()
                .body(response);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<GeneralResponseDTO<Void>> illegalArgumentErrorHandler(final HttpServletRequest httpServletRequest,
                                                                                   final HttpServletResponse httpServletResponse, IllegalArgumentException illegalArgumentException) {
        GeneralResponseDTO<Void> response = new GeneralResponseDTO<>();
        response.setStatusCode(Constants.StatusCodeApi.ERROR_GENERIC);
        response.setMessage(illegalArgumentException.getMessage());
        response.setServiceName(illegalArgumentException.getClass().getName());
        return ResponseEntity.badRequest()
                .body(response);

    }

}