package com.pragma.users.infrastructure.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InfrastructureErrorDto {
    private String message;
}
