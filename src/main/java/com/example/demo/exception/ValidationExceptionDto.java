package com.example.demo.exception;

import java.io.Serializable;

import org.springframework.validation.FieldError;


public class ValidationExceptionDto implements Serializable {
    private String name;
    private String message;

    public ValidationExceptionDto(FieldError error) {
        this.name = error.getField();
        this.message = error.getDefaultMessage();
    }

    public ValidationExceptionDto(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public ValidationExceptionDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}