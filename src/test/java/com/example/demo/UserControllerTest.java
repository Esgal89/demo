package com.example.demo;

import com.example.demo.controller.UserController;
import com.example.demo.dto.UpdateUserDto;
import com.example.demo.dto.UpdateUserFieldsDto;
import com.example.demo.dto.UserSearchDto;
import com.example.demo.exception.CustomExceptionHandler;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserControllerTest {


    final String USERS_LINK = "/users";

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private Validator mockValidator;

    final private ErrorAttributes errorAttributes = new DefaultErrorAttributes();


    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setValidator(mockValidator)
                .setControllerAdvice(new CustomExceptionHandler(errorAttributes))
                .build();
    }

    @Test
    void createUserTest() throws Exception {
        String json = """
                        {
                          "email": "example@example.com",
                          "firstName": "John",
                          "lastName": "Doe",
                          "birthDate": "2006-04-26",
                          "address": "123 Main St",
                          "phoneNumber": "123-456-7890"
                        }
                """;

        mockMvc.perform(post(USERS_LINK + "/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(userService).create(any());
    }


    @Test
    void updateUserTest() throws Exception {

        String json = """
                        {
                          "email": "example@example.com",
                          "firstName": "John",
                          "lastName": "Doe",
                          "birthDate": "2006-04-26",
                          "address": "123 Main St",
                          "phoneNumber": "123-456-7890"
                        }
                """;

        long userId = 1L;


        mockMvc.perform(put(USERS_LINK + "/update/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService).update(eq(userId), any(UpdateUserDto.class));
    }


    @Test
    void testUpdateUserFields() throws Exception {

        long userId = 1L;
        String json = """
                        {
                          "email": "example@example.com"
                        }
                """;


        mockMvc.perform(patch(USERS_LINK + "/update/fields/{userId}", userId)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService).updateUserFields(eq(userId), any(UpdateUserFieldsDto.class));
    }


    @Test
    void testDeleteUser() throws Exception {

        long userId = 1L;

        mockMvc.perform(delete(USERS_LINK + "/delete/{userId}", userId))
                .andExpect(status().isNoContent());

        verify(userService).delete(userId);
    }

    @Test
    void testSearchUser() throws Exception {
        String json = """
                        {
                            "fromDate" : "1980-01-01",
                            "toDate" : "2093-01-01"
                        }
                """;

        mockMvc.perform(get(USERS_LINK + "/search")
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService).search(any(UserSearchDto.class));

    }


}
