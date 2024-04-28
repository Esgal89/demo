package com.example.demo.validator;

import com.example.demo.annotation.AgeThreshold;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<AgeThreshold, LocalDate> {

    @Value("${min.age}")
    private int minAge;

    @Override
    public void initialize(AgeThreshold constraintAnnotation) {
        // Initializes the validator in preparation for #isValid calls
    }

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext constraintValidatorContext) {

        if (birthDate == null) {
            return true;
        }

        LocalDate currentDate = LocalDate.now();
        long age = Period.between(birthDate, currentDate).getYears();
        return age >= minAge;
    }
}
