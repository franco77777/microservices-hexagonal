package com.pragma.square.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientRequest {
    @NotNull(message = "plateId name is required")
    private Long plateId;
    @NotNull(message = "quantity name is required")
    private Integer quantity;
}
