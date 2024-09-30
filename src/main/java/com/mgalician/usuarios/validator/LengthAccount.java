package com.mgalician.usuarios.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.mgalician.usuarios.helper.MensajeHelper;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LengthAccountValidator.class)
public @interface LengthAccount {
    String message() default MensajeHelper.ERROR_NUMERO_CUENTA_LONGITUD_10;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
