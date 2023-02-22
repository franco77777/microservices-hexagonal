//package com.pragma.square.application.validators.nit;
//
//import jakarta.validation.Constraint;
//import jakarta.validation.Payload;
//
//import java.lang.annotation.*;
//
//@Target({ElementType.FIELD,ElementType.PARAMETER})
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//@Constraint(validatedBy = ImplValidateNumbers.class)
//public @interface ValidateNumbers {
//
//    public String message() default "Invalid";
//
//    Class<?>[] groups() default {};
//
//    Class<? extends Payload>[] payload() default {};
//}