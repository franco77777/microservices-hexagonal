package com.pragma.square.application.request;


import com.pragma.square.application.validators.IValidateRestaurantName;


import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class RestaurantRequestDto {
    @NotBlank(message = "Name is required")
   @NotNull(message = "Name is required")
   @IValidateRestaurantName(message = "Name must have letters")
    private String name;
   @NotBlank(message = "Address is required")
   @NotNull(message = "Address is required")
   private String address;

   @NotBlank(message = "telephone is required")
   @Length(max = 13, message = "telephone should not have more than 13 digits")
   @Pattern(regexp="^[0-9\\-\\+]{5,13}$", message = "incorrect telephone characters")
   @NotNull(message = "telephone is required")
    private String telephone;
    @NotBlank(message= "url is required")
    @NotNull(message= "url is required")
    private String url;
    //@NotNull(message="userId is required")
    private Long userId;
    //
    @NotNull(message = "nit is required")
    @NotBlank(message = "nit is required")
    @Pattern(regexp="^[1-9]+[0-9]*$",message = "incorrect nit characters")
    private String nit;
}
