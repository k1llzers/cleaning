package com.naukma.cleaning.controllers;

import com.naukma.cleaning.models.dtos.UserDto;
import com.naukma.cleaning.services.authenticationService.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FormController {
    private final AuthenticationService authenticationService;
    @Autowired
    public FormController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/main/sign")
    public String showForm() {
        return "signup-form";
    }

    @Operation(summary = "Add user", description = "Add user")
    @PostMapping("/main/success")
//    public String addUser(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String pass) {
//        authenticationService.register(name,email,pass);
    public String addUser(@ModelAttribute @Valid UserDto userDto) {
        authenticationService.register(userDto.getName(),userDto.getEmail(),userDto.getPassword());
        return "index";
    }
}
