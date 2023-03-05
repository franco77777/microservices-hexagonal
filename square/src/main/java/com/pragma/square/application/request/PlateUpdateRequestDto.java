package com.pragma.square.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlateUpdateRequestDto {
    @Pattern(regexp="^[0-9]{1,50}$", message = "The price must be greater than 0 and only numbers are allowed")
    @NotNull(message = "price cannot be null")
    @NotBlank(message = "price cannot be blank")
    private String price;
    @NotNull(message = "plate cannot be null")
    @NotBlank(message = "plate cannot be blank")
    private String description;
}
