package com.example.demo;

import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private NotFoundException notFoundException;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        // Arrange
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setEmail("example@example.com");
        createUserDto.setFirstName("John");
        createUserDto.setLastName("Doe");
        createUserDto.setBirthDate(LocalDate.of(2000, 1, 1));

        User user = new User();
        user.setEmail(createUserDto.getEmail());
        user.setFirstName(createUserDto.getFirstName());
        user.setLastName(createUserDto.getLastName());
        user.setBirthDate(createUserDto.getBirthDate());

        when(userRepository.save(any(User.class))).thenReturn(user);

        UserViewDto userViewDto = new UserViewDto();
        userViewDto.setEmail(user.getEmail());
        userViewDto.setFirstName(user.getFirstName());
        userViewDto.setLastName(user.getLastName());
        userViewDto.setBirthDate(user.getBirthDate());

        when(modelMapper.map(user, UserViewDto.class)).thenReturn(userViewDto);

        // Act
        UserViewDto createdUser = userService.create(createUserDto);

        // Assert
        assertNotNull(createdUser);
        assertEquals(createUserDto.getEmail(), createdUser.getEmail());
        assertEquals(createUserDto.getFirstName(), createdUser.getFirstName());
        assertEquals(createUserDto.getLastName(), createdUser.getLastName());
        assertEquals(createUserDto.getBirthDate(), createdUser.getBirthDate());
    }

    @Test
    void testUpdateUser() {
        // Arrange
        Long userId = 1L;
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setEmail("new@example.com");
        updateUserDto.setFirstName("UpdatedFirstName");
        updateUserDto.setLastName("UpdatedLastName");
        updateUserDto.setBirthDate(LocalDate.of(1990, 1, 1));

        User user = new User();
        user.setId(userId);
        user.setEmail("old@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserViewDto userViewDto = new UserViewDto();
        userViewDto.setEmail(updateUserDto.getEmail());
        userViewDto.setFirstName(updateUserDto.getFirstName());
        userViewDto.setLastName(updateUserDto.getLastName());
        userViewDto.setBirthDate(updateUserDto.getBirthDate());

        when(modelMapper.map(user, UserViewDto.class)).thenReturn(userViewDto);

        // Act
        UserViewDto updatedUser = userService.update(userId, updateUserDto);

        // Assert
        assertNotNull(updatedUser);
        assertEquals("new@example.com", updatedUser.getEmail());
        assertEquals("UpdatedFirstName", updatedUser.getFirstName());
        assertEquals("UpdatedLastName", updatedUser.getLastName());
        assertEquals(LocalDate.of(1990, 1, 1), updatedUser.getBirthDate());
    }

    @Test
    void testUpdateUserNotFound() {
        // Arrange
        Long userId = 1L;
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setEmail("new@example.com");
        updateUserDto.setFirstName("UpdatedFirstName");
        updateUserDto.setLastName("UpdatedLastName");
        updateUserDto.setBirthDate(LocalDate.of(1990, 1, 1));


        when(userRepository.findById(userId)).thenThrow(notFoundException);

        // Act & Assert

        assertThrows(NotFoundException.class, () -> userService.update(userId, updateUserDto));
    }

    @Test
    void testUpdateUserFieldsUser() {
        // Arrange
        Long userId = 1L;
        UpdateUserFieldsDto updateUserFieldsDto = new UpdateUserFieldsDto();
        updateUserFieldsDto.setEmail("new@example.com");
        updateUserFieldsDto.setFirstName("UpdatedFirstName");

        User user = new User();
        user.setId(userId);
        user.setEmail("old@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserViewDto userViewDto = new UserViewDto();
        userViewDto.setEmail(updateUserFieldsDto.getEmail());
        userViewDto.setFirstName(updateUserFieldsDto.getFirstName());

        when(modelMapper.map(user, UserViewDto.class)).thenReturn(userViewDto);

        // Act
        UserViewDto updatedUser = userService.updateUserFields(userId, updateUserFieldsDto);

        // Assert
        assertNotNull(updatedUser);
        assertEquals("new@example.com", updatedUser.getEmail());
        assertEquals("UpdatedFirstName", updatedUser.getFirstName());
    }

    @Test
    void testUpdateUserFieldsUserNotFound() {
        // Arrange
        Long userId = 1L;
        UpdateUserFieldsDto updateUserFieldsDto = new UpdateUserFieldsDto();
        updateUserFieldsDto.setEmail("new@example.com");
        updateUserFieldsDto.setFirstName("UpdatedFirstName");

        when(userRepository.findById(userId)).thenThrow(notFoundException);

        // Act & Assert

        assertThrows(NotFoundException.class, () -> userService.updateUserFields(userId, updateUserFieldsDto));
    }

    @Test
    void testDeleteUser() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        userService.delete(userId);

        // Assert
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void testDeleteUserNotFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenThrow(notFoundException);

        // Act & Assert

        assertThrows(NotFoundException.class, () -> userService.delete(userId));
    }


    @Test
    void testSearch() {
        // Arrange
        LocalDate fromDate = LocalDate.of(1990, 1, 1);
        LocalDate toDate = LocalDate.of(2000, 12, 31);

        UserSearchDto userSearchDto = new UserSearchDto(fromDate, toDate);

        User user1 = new User();
        user1.setEmail("user1@example.com");

        User user2 = new User();
        user2.setEmail("user2@example.com");

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findByBirthDateBetween(fromDate, toDate)).thenReturn(users);

        UserViewDto userViewDto1 = new UserViewDto();
        userViewDto1.setEmail("user1@example.com");


        UserViewDto userViewDto2 = new UserViewDto();
        userViewDto2.setEmail("user2@example.com");


        when(modelMapper.map(user1, UserViewDto.class)).thenReturn(userViewDto1);
        when(modelMapper.map(user2, UserViewDto.class)).thenReturn(userViewDto2);

        // Act
        List<UserViewDto> result = userService.search(userSearchDto);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("user1@example.com", result.get(0).getEmail());
        assertEquals("user2@example.com", result.get(1).getEmail());

    }


}
