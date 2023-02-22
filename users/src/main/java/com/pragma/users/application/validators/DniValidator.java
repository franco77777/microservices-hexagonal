//package com.pragma.users.application.validators;
//
//import com.pragma.users.application.handler.IObjectHandler;
//import com.pragma.users.infrastructure.output.repository.IUserRepository;
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//import lombok.RequiredArgsConstructor;
//import org.hibernate.mapping.Any;
//
//@RequiredArgsConstructor
//public class DniValidator implements ConstraintValidator<IDniValidator, Integer> {
//    private final IObjectHandler objectHandler;
//    private final IUserRepository repository;
//
//    @Override
//    public boolean isValid(Integer dni, ConstraintValidatorContext constraintValidatorContext) {
//     if(dni <= 0) {return false;}
//     return true;
//    }};