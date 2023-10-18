package com.naukma.cleaning.controllers;

import com.naukma.cleaning.models.user.UserDto;
import com.naukma.cleaning.services.userService.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id){
        return userService.getUser(id);
    }

    @GetMapping("/by-email")
    public UserDto getUserByEmail(@RequestParam String email){
        return userService.getUserByEmail(email);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PostMapping
    public UserDto addUser(@RequestBody @Valid UserDto userDto){
        return userService.createUser(userDto);
    }

    @PutMapping
    public UserDto updateUser(@RequestBody @Valid UserDto userDto){
        return userService.editUser(userDto);
    }
}
