package com.naukma.cleaning.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naukma.cleaning.models.dtos.UserDto;
import com.naukma.cleaning.models.user.Role;
import org.junit.jupiter.api.Test;
import com.naukma.cleaning.controllers.UserController;
import com.naukma.cleaning.services.authenticationService.AuthenticationService;
import com.naukma.cleaning.services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
//@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = {UserController.class}, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private AuthenticationService authenticationService;

    @Test
    void addNewUserTest() throws Exception {
        UserDto userDto = new UserDto("dhhhhh","b@:}u~3DC:[b@:}u~3DC:[","hdh", Role.Employee);
        when(authenticationService.register(anyString(),anyString(),anyString())).thenReturn(userDto);
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(authenticationService, times(1)).register(anyString(),anyString(),anyString());
    }

    @Test
    void addNewUserTest_IncorrectRequestParam() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                        "  \"email\": \"hdh\",\n" +
                        "  \"name\": null,\n" +
                        "  \"password\": \"b@:}u~3DC:[b@:}u~3DC:[\",\n" +
                        "  \"role\": \"Employee\",\n" +
                        "  \"id\": \"3\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getUserById_SerializationTest() throws Exception {
        UserDto userDto = new UserDto("John","password","john_user@gmail.com", Role.User);
        when(userService.getUserDto(1l)).thenReturn(userDto);
        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(jsonPath("$.email").value("john_user@gmail.com"))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.password").isNotEmpty())
                .andExpect(jsonPath("$.role").value("User"))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(status().isOk());
    }

    @Test
    void updateUserTest() throws Exception {
        UserDto updatedUserDto = new UserDto("UpdatedName", "updatedPass", "updated@gmail.com", Role.Employee);
        when(userService.editUser(any(UserDto.class))).thenReturn(updatedUserDto);
        mockMvc.perform(put("/users", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedUserDto)))
                .andExpect(jsonPath("$.name").value("UpdatedName"))
                .andExpect(jsonPath("$.password").value("updatedPass"))
                .andExpect(jsonPath("$.email").value("updated@gmail.com"))
                .andExpect(jsonPath("$.role").value("Employee"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUserTest() throws Exception {
        mockMvc.perform(delete("/users/{id}", 1L))
                .andExpect(status().isNoContent());
        verify(userService, times(1)).deleteUser(1L);
    }
}
