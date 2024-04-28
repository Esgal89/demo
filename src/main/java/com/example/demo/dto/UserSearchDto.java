package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class UserSearchDto {

    @NotNull
    LocalDate fromDate;

    @NotNull
    LocalDate toDate;

    public UserSearchDto(LocalDate fromDate, LocalDate toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
