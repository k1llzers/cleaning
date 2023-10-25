package com.naukma.cleaning.controllers;

import com.naukma.cleaning.models.dtos.UserDto;
import com.naukma.cleaning.models.user.User;
import com.naukma.cleaning.services.userService.UserService;
import com.naukma.cleaning.utils.exceptions.EmailDuplicateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User API", description = "Endpoint for operations with users (customers/staff)")
public class UserController {
    private final UserService userService;
    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Operation(summary = "Get user by id", description = "Get user by id")
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        throw new EmailDuplicateException();
        // return userService.getUser(id);
    }

    @Operation(summary = "Get user by email", description = "Get user by email")
    @GetMapping("/by-email")
    public UserDto getUserByEmail(@RequestParam String email) {
        RestTemplate restTemplate = new RestTemplate();
        String resource = "http://worldtimeapi.org/api/timezone/Europe/Kyiv";
        ResponseEntity<String> time = restTemplate.getForEntity(resource, String.class);
        log.info("time from api:" + time.getBody());
        return userService.getUserDtoByEmail(email);
    }

    @Operation(summary = "Change user", description = "Change user")
    @PutMapping
    public UserDto updateUser(@RequestBody @Valid UserDto userDto) {
        return userService.editUser(userDto);
    }

    @Operation(summary = "Add user", description = "Add user")
    @PostMapping
    public UserDto addUser(@RequestBody @Valid UserDto userDto) {
        return userService.createUser(userDto);
    }

    @Operation(summary = "Delete user", description = "Delete user")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @ExceptionHandler({EmailDuplicateException.class})
    public void handleException(Exception ex) {
        log.warn("Excep");
    }
}
