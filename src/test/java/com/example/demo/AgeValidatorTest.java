package com.example.demo;

import com.example.demo.validator.AgeValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;


import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
public class AgeValidatorTest {


    private AgeValidator ageValidator;

    @BeforeEach
    public void init() {
        ageValidator = new AgeValidator();
    }

    @ParameterizedTest
    @NullSource
    void isValid_ImageIsNull_True(LocalDate birthDate) {
        assertTrue(ageValidator.isValid(birthDate, null));
    }

    @ParameterizedTest
    @MethodSource("dateWithValidContentType")
    void isValid_ImageHasValidContentType_True(LocalDate birthDate) {
        assertTrue(ageValidator.isValid(birthDate, null));
    }

    @ParameterizedTest
    @MethodSource("dateWithInvalidContentType")
    void isValid_ImageHasInvalidContentType_False(LocalDate birthDate) {
        assertFalse(ageValidator.isValid(birthDate, null));
    }

    private static Stream<LocalDate> dateWithValidContentType() {

        return Stream.of(LocalDate.now().minusYears(10));
    }

    private static Stream<LocalDate> dateWithInvalidContentType() {

        return Stream.of(LocalDate.now().plusYears(10));
    }


}