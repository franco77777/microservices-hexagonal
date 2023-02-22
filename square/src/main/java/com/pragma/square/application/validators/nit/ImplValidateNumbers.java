//package com.pragma.square.application.validators.nit;
//
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//import org.hibernate.mapping.Any;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class ImplValidateNumbers implements ConstraintValidator<ValidateNumbers, Integer> {
//
//    @Override
//    public boolean isValid(Integer nit, ConstraintValidatorContext constraintValidatorContext) {
//
//      if(nit == null) {return false;}
//      String request = nit.toString();
//        for (int x = 0; x < request.length(); x++) {
//            char c = request.charAt(x);
//            if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
//                return false;
//            }
//        }
//      return true;}    }
