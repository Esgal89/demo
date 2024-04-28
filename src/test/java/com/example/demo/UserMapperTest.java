package com.example.demo;

import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.UpdateUserFieldsDto;
import com.example.demo.dto.UpdateUserDto;
import com.example.demo.dto.UserViewDto;
import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTest {

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    void testCreateUserDtoToEntityMapping() {

        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setEmail("example@example.com");
        createUserDto.setFirstName("John");
        createUserDto.setLastName("Doe");
        createUserDto.setBirthDate(LocalDate.of(2000, 1, 1));
        createUserDto.setAddress("123 Main St");
        createUserDto.setPhoneNumber("123-456-7890");

        User user = modelMapper.map(createUserDto, User.class);

        assertEquals(createUserDto.getEmail(), user.getEmail());
        assertEquals(createUserDto.getFirstName(), user.getFirstName());
        assertEquals(createUserDto.getLastName(), user.getLastName());
        assertEquals(createUserDto.getBirthDate(), user.getBirthDate());
        assertEquals(createUserDto.getAddress(), user.getAddress());
        assertEquals(createUserDto.getPhoneNumber(), user.getPhoneNumber());
    }

    @Test
    void testUpdateUserDtoToEntityMapping() {

        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setEmail("example@example.com");
        updateUserDto.setFirstName("John");
        updateUserDto.setLastName("Doe");
        updateUserDto.setBirthDate(LocalDate.of(2000, 1, 1));
        updateUserDto.setAddress("123 Main St");
        updateUserDto.setPhoneNumber("123-456-7890");

        User user = modelMapper.map(updateUserDto, User.class);

        assertEquals(updateUserDto.getEmail(), user.getEmail());
        assertEquals(updateUserDto.getFirstName(), user.getFirstName());
        assertEquals(updateUserDto.getLastName(), user.getLastName());
        assertEquals(updateUserDto.getBirthDate(), user.getBirthDate());
        assertEquals(updateUserDto.getAddress(), user.getAddress());
        assertEquals(updateUserDto.getPhoneNumber(), user.getPhoneNumber());
    }


    @Test
    void testPartialUpdateUserDtoToEntityMapping() {

        UpdateUserFieldsDto updateUserFieldsDto = new UpdateUserFieldsDto();
        updateUserFieldsDto.setEmail("example@example.com");
        updateUserFieldsDto.setFirstName("John");
        updateUserFieldsDto.setLastName("Doe");
        updateUserFieldsDto.setBirthDate(LocalDate.of(2000, 1, 1));
        updateUserFieldsDto.setAddress("123 Main St");
        updateUserFieldsDto.setPhoneNumber("123-456-7890");

        User user = modelMapper.map(updateUserFieldsDto, User.class);

        assertEquals(updateUserFieldsDto.getEmail(), user.getEmail());
        assertEquals(updateUserFieldsDto.getFirstName(), user.getFirstName());
        assertEquals(updateUserFieldsDto.getLastName(), user.getLastName());
        assertEquals(updateUserFieldsDto.getBirthDate(), user.getBirthDate());
        assertEquals(updateUserFieldsDto.getAddress(), user.getAddress());
        assertEquals(updateUserFieldsDto.getPhoneNumber(), user.getPhoneNumber());
    }

    @Test
    void testEntityToDtoMapping() {

        User user = new User();
        user.setEmail("example@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setBirthDate(LocalDate.of(2000, 1, 1));
        user.setAddress("123 Main St");
        user.setPhoneNumber("123-456-7890");

        UserViewDto userViewDto = modelMapper.map(user, UserViewDto.class);

        assertEquals(user.getEmail(), userViewDto.getEmail());
        assertEquals(user.getFirstName(), userViewDto.getFirstName());
        assertEquals(user.getLastName(), userViewDto.getLastName());
        assertEquals(user.getBirthDate(), userViewDto.getBirthDate());
        assertEquals(user.getAddress(), userViewDto.getAddress());
        assertEquals(user.getPhoneNumber(), userViewDto.getPhoneNumber());
    }
}

