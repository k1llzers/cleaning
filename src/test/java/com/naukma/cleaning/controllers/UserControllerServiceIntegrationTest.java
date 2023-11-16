package com.naukma.cleaning.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naukma.cleaning.dao.UserDao;
import com.naukma.cleaning.dao.entities.UserEntity;
import com.naukma.cleaning.models.dtos.UserDto;
import com.naukma.cleaning.models.user.Role;
import com.naukma.cleaning.services.authenticationService.AuthenticationServiceImpl;
import com.naukma.cleaning.services.userService.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {UserController.class}, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@Import({UserServiceImpl.class, AuthenticationServiceImpl.class})
class UserControllerServiceIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserDao userDao;
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void addNewUserTest() throws Exception {
        when(userDao.save(any())).thenReturn(new UserEntity(5, "user", "password",
                "user_test@gmail.com", Role.User, List.of()));
        UserDto userDto = new UserDto("dhhhhh", "b@:}u~3DC:[b@:}u~3DC:[", "hdh", Role.Employee);
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(userDao, times(1)).save(any(UserEntity.class));
    }

    @Test
    void deleteUserTest() throws Exception {
        mockMvc.perform(delete("/users/{id}", 1))
                .andExpect(status().isNoContent());
        verify(userDao, times(1)).deleteById(anyLong());
    }

    @Test
    void updateUserTest() throws Exception {
        when(userDao.save(any())).thenReturn(new UserEntity(5, "user", "password",
                "user_test@gmail.com", Role.User, List.of()));
        UserDto userDto = new UserDto("user", "password", "user_test@gmail.com", Role.Employee);
        mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(userDao, times(1)).save(any(UserEntity.class));
    }

    @Test
    void getUserByIdTest() throws Exception {
        UserEntity entity = new UserEntity(5, "John", "password",
                "john_user@gmail.com", Role.User, List.of());
        when(userDao.findById(anyLong())).thenReturn(Optional.of(entity));
        mockMvc.perform(get("/users/{id}",1))
                .andExpect(jsonPath("$.email").value("john_user@gmail.com"))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.password").isNotEmpty())
                .andExpect(jsonPath("$.role").value("User"))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(status().isOk());
        verify(userDao, times(1)).findById(anyLong());
    }

    @Test
    void getUserByEmailTest() throws Exception {
        UserEntity entity = new UserEntity(5, "Harry", "password",
                "harry_employee@gmail.com", Role.Employee, List.of());
        when(userDao.findUserEntityByEmail(anyString())).thenReturn(entity);
        mockMvc.perform(get("/users/by-email/{email}","harry_employee@gmail.com"))
                .andExpect(jsonPath("$.email").value("harry_employee@gmail.com"))
                .andExpect(jsonPath("$.name").value("Harry"))
                .andExpect(jsonPath("$.password").isNotEmpty())
                .andExpect(jsonPath("$.role").value("Employee"))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(status().isOk());
        verify(userDao, times(1)).findUserEntityByEmail(anyString());
    }
}