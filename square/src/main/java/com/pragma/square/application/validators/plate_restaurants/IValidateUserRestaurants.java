package com.pragma.square.application.validators.plate_restaurants;

import com.pragma.square.application.validators.restaurant_name.IValidateRestaurantName;
import com.pragma.square.application.validators.restaurant_name.ValidateRestaurantName;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidateUserRestaurants.class)
public @interface IValidateUserRestaurants   {
    public String message() default "Invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
