package com.naukma.cleaning.controllers;

import com.naukma.cleaning.models.dtos.UserDto;
import com.naukma.cleaning.services.authenticationService.AuthenticationService;
import com.naukma.cleaning.services.userService.UserService;
import com.naukma.cleaning.utils.exceptions.EmailDuplicateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User API", description = "Endpoint for operations with users (customers/staff)")
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Operation(summary = "Get user by id", description = "Get user by id")
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserDto(id);
    }

    @Operation(summary = "Get user by email", description = "Get user by email")
    @GetMapping("/by-email/{email}")
    public UserDto getUserByEmail(@PathVariable String email) {
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
        return authenticationService.register(userDto.getName(),userDto.getEmail(),userDto.getPassword());
    }

    @Operation(summary = "Delete user", description = "Delete user")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @ExceptionHandler({EmailDuplicateException.class})
    public void handleException(Exception ex) {
        log.warn("Exception occurred");
    }
}
