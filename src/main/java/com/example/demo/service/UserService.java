package com.example.demo.service;

import com.example.demo.dto.*;

import java.util.List;

public interface UserService {

    UserViewDto create(CreateUserDto createUserDto);

    UserViewDto update(Long userId, UpdateUserDto userDto);

    UserViewDto updateUserFields(Long userId, UpdateUserFieldsDto userDto);

    void delete(Long userId);

    List<UserViewDto> search(UserSearchDto userSearchDto);
}
