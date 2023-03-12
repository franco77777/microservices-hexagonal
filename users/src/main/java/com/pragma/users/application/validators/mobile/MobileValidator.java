package com.pragma.users.application.validators.mobile;

import com.pragma.users.application.validators.email.IEmailExists;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileValidator implements ConstraintValidator<IMobileValidator, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(!Objects.equals(s.charAt(0),'+')) return false;
        String numbers = s.substring(1);
        String regex = "\\b\\d+\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(numbers);
        return matcher.matches();
    }}
