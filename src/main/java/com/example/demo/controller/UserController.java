package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.Valid;

import java.util.List;


@RestController
@Validated

@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;

    }

    // 2.1. Create user

    @PostMapping("/create")
    @Operation(summary = "Create a new user")
    public ResponseEntity<UserViewDto> createUser(
            @Valid @RequestBody CreateUserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.create(userDto));
    }

    // 2.2. Update one/some user fields
    @PatchMapping("/update/fields/{userId}")
    @Operation(summary = "Update one or some user fields")
    public ResponseEntity<UserViewDto> updateUserFields(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateUserFieldsDto userDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.updateUserFields(userId, userDto));
    }

    // 2.3. Update all user fields
    @PutMapping("/update/{userId}")
    @Operation(summary = "Update all user fields")
    public ResponseEntity<UserViewDto> updateAllUserFields(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateUserDto userDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.update(userId, userDto));
    }

    // 2.4. Delete user
    @DeleteMapping("/delete/{userId}")
    @Operation(summary = "Delete a user")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 2.5. Search for users by birth date range
    @GetMapping("/search")
    @Operation(summary = "Search for users by birth date range")
    public ResponseEntity<List<UserViewDto>> searchUsersByBirthDateRange(
            @Valid @RequestBody UserSearchDto userSearchDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.search(userSearchDto));
    }


}
