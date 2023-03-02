package com.pragma.square.application.request;

import com.pragma.square.infrastructure.output.entity.RestaurantEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlateRequestDto {
    @NotNull(message = "Please provide plate name")
    @NotBlank(message = "Please provide plate name")
    private String name;
    @Pattern(regexp="^[0-9]{1,50}$", message = "The price must be greater than 0 and only numbers are allowed")
    @NotBlank(message = "Please provide plate price")
    @NotNull(message = "Please provide plate price")
    private String price;
    @NotBlank(message = "Please provide plate description")
    @NotNull(message = "Please provide plate description")
    private String description;
    @NotBlank(message = "Please provide plate image")
    @NotNull(message = "Please provide plate image")
    private String imageUrl;






}

