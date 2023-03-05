package com.pragma.square.domain.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class DomainException extends RuntimeException{
    private HttpStatus status;
    public DomainException( String message,HttpStatus status) {
        super(message);
        this.status = status;
    }
}
