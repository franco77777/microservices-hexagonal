package com.pragma.users.application.validators.email;

import com.pragma.users.application.handler.IUserHandler;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailExists implements ConstraintValidator<IEmailExists, String> {
    private final IUserHandler objectHandler;

    @Override
        public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return objectHandler.emailExists(email);
    }}
