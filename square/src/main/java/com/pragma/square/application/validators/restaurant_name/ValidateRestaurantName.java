package com.pragma.square.application.validators.restaurant_name;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidateRestaurantName implements ConstraintValidator<IValidateRestaurantName, String> {
    @Override
    public boolean isValid(String employeeType, ConstraintValidatorContext constraintValidatorContext) {
        if(employeeType == null){return false;}
        for (int x = 0; x < employeeType.length(); x++) {
            char c = employeeType.charAt(x);
            if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                return true;
            }
        }
        return false;
    }

        //System.out.println(result);
        //List<String> employeeTypes = Arrays.asList("Permanent", "vendor");
        //return employeeTypes.contains(employeeType);
    }
