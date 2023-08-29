package com.api.bank.infrastructure.controller.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MovementRequest {

    @NonNull
    private String accountNumber;
    @NonNull
    private String type;
    @NonNull
    private BigDecimal amount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateMovement;

    public MovementRequest() {
    }
}
