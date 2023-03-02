package com.pragma.square.application.validators.plate_restaurants;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidateUserRestaurants implements ConstraintValidator<IValidateUserRestaurants, String> {
    @Override
    public boolean isValid(String restaurantId, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
