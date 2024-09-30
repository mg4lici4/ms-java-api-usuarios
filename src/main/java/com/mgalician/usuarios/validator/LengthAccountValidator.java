package com.mgalician.usuarios.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LengthAccountValidator implements ConstraintValidator<LengthAccount, Long> {
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Permitimos valores nulos
        }
        String stringValue = String.valueOf(value);
        return stringValue.length() == 10;
    }

}