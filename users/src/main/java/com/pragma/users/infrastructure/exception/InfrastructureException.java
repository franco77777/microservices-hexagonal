package com.pragma.users.infrastructure.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class InfrastructureException extends RuntimeException{
    private HttpStatus status;
    public InfrastructureException( String message,HttpStatus status) {
        super(message);
        this.status = status;
    }
}
