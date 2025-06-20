package com.sia.rest.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidDateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDate {

    String message() default "Uncorrect date. formate dd.MM.yyyy";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}