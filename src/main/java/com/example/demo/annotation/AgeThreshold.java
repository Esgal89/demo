package com.example.demo.annotation;

import com.example.demo.validator.AgeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.example.demo.constants.ValidationConstants.AGE_ERROR_MESSAGE;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface AgeThreshold {

    String message() default AGE_ERROR_MESSAGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
