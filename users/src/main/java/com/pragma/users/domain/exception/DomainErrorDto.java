package com.pragma.users.domain.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DomainErrorDto {
    private String message;
}
