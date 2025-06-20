package com.sia.rest.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumTypeValidator implements ConstraintValidator<ValidEnum, Enum<?>> {

    private Enum<?>[] enumConstants;

    @Override
    public void initialize(ValidEnum annotation) {
        enumConstants = annotation.enumClass().getEnumConstants();
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        if (value == null) return false;

        for (Enum<?> e : enumConstants) {
            if (e.name().equals(value.name())) {
                return true;
            }
        }
        return false;
    }
}