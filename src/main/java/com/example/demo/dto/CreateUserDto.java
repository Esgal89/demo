package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.annotation.AgeThreshold;
import com.example.demo.constants.ValidationConstants;
import jakarta.validation.constraints.*;

public class CreateUserDto {

    @NotBlank
    @Email(message = ValidationConstants.INVALID_EMAIL)
    @Size(max = 255)
    private String email;

    @Pattern(
            regexp = ValidationConstants.NAME_REGEXP,
            message = ValidationConstants.NAME_MESSAGE)
    @Size(max = 50)
    @NotBlank
    private String firstName;

    @Pattern(
            regexp = ValidationConstants.NAME_REGEXP,
            message = ValidationConstants.NAME_MESSAGE)
    @Size(max = 50)
    @NotBlank
    private String lastName;

    @NotNull
    @AgeThreshold
    private LocalDate birthDate;

    @Pattern(
            regexp = ValidationConstants.ADDRESS_REGEXP,
            message = ValidationConstants.ADDRESS_MESSAGE)
    @Size(max = 255)
    private String address;

    @Pattern(
            regexp = ValidationConstants.PHONE_REGEXP,
            message = ValidationConstants.PHONE_MESSAGE)
    @Size(max = 15)
    private String phoneNumber;

    public CreateUserDto(String email, String firstName, String lastName, LocalDate birthDate, String address, String phoneNumber) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public CreateUserDto() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
