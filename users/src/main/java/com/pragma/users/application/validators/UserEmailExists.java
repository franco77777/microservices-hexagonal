package com.pragma.users.application.validators;

import com.pragma.users.application.handler.IObjectHandler;
import com.pragma.users.infrastructure.output.repository.IUserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserEmailExists implements ConstraintValidator<IUserEmailExists, String> {
    private final IObjectHandler objectHandler;

    @Override
        public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return objectHandler.emailExists(email);
    }}
