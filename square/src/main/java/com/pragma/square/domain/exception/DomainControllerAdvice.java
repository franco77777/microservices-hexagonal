package com.pragma.square.domain.exception;

import com.pragma.square.infrastructure.exception.InfrastructureErrorDto;
import com.pragma.square.infrastructure.exception.InfrastructureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class DomainControllerAdvice {
    @ExceptionHandler(value = DomainException.class)
    public ResponseEntity<DomainErrorDto> businessExceptionHandler(DomainException ex) {
        DomainErrorDto error = DomainErrorDto.builder().message(ex.getMessage()).build();
        return new ResponseEntity<>(error, ex.getStatus());
    }
}