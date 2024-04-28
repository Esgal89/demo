package com.example.demo.dto;

import com.example.demo.annotation.AgeThreshold;
import com.example.demo.constants.ValidationConstants;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;

import java.time.LocalDate;


public class UpdateUserFieldsDto {

    @Nullable
    @Email(message = ValidationConstants.INVALID_EMAIL)
    @Size(max = 255)
    private String email;

    @Nullable
    @Pattern(
            regexp = ValidationConstants.NAME_REGEXP,
            message = ValidationConstants.NAME_MESSAGE)
    @Size(max = 50)
    private String firstName;

    @Nullable
    @Pattern(
            regexp = ValidationConstants.NAME_REGEXP,
            message = ValidationConstants.NAME_MESSAGE)
    @Size(max = 50)
    private String lastName;

    @Nullable
    @AgeThreshold
    private LocalDate birthDate;

    @Nullable
    @Pattern(
            regexp = ValidationConstants.ADDRESS_REGEXP,
            message = ValidationConstants.ADDRESS_MESSAGE)
    @Size(max = 255)
    private String address;

    @Nullable
    @Pattern(
            regexp = ValidationConstants.PHONE_REGEXP,
            message = ValidationConstants.PHONE_MESSAGE)
    @Size(max = 15)
    private String phoneNumber;

    public UpdateUserFieldsDto(String email, String firstName, String lastName, LocalDate birthDate, String address, String phoneNumber) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public UpdateUserFieldsDto() {

    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Nullable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Nullable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Nullable
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Nullable
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Nullable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
