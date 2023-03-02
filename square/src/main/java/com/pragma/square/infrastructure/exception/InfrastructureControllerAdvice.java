package com.pragma.square.infrastructure.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class InfrastructureControllerAdvice {
    @ExceptionHandler(value = InfrastructureException.class)
    public ResponseEntity<InfrastructureErrorDto> businessExceptionHandler(InfrastructureException ex){
        InfrastructureErrorDto error = InfrastructureErrorDto.builder().message(ex.getMessage()).build();
        return new ResponseEntity<>(error, ex.getStatus());
    }
}
