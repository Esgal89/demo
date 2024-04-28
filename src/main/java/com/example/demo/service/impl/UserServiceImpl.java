package com.example.demo.service.impl;

import com.example.demo.constants.ErrorMessages;
import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserViewDto create(CreateUserDto userDto) {
        User user = new User();

        modelMapper.map(userDto, user);
        user = userRepository.save(user);

        return modelMapper.map(user, UserViewDto.class);
    }

    @Override
    public UserViewDto update(Long userId, UpdateUserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.USER_NOT_FOUND + userId));

        modelMapper.map(userDto, user);

        user = userRepository.save(user);
        return modelMapper.map(user, UserViewDto.class);
    }

    @Override
    public UserViewDto updateUserFields(Long userId, UpdateUserFieldsDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.USER_NOT_FOUND + userId));

        modelMapper.map(userDto, user);

        user = userRepository.save(user);
        return modelMapper.map(user, UserViewDto.class);
    }

    @Override
    public void delete(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.USER_NOT_FOUND + userId));
        userRepository.delete(user);
    }

    @Override
    public List<UserViewDto> search(UserSearchDto userSearchDto) {

        if (userSearchDto.getFromDate().isAfter(userSearchDto.getToDate())) {
            throw new IllegalArgumentException(ErrorMessages.DATE_RANGE_INVALID);
        }

        List<User> users = userRepository.findByBirthDateBetween(userSearchDto.getFromDate(), userSearchDto.getToDate());
        return users.stream()
                .map(user -> modelMapper.map(user, UserViewDto.class))
                .collect(Collectors.toList());
    }


}

