package com.naukma.cleaning.viewControllers;

import com.naukma.cleaning.models.dtos.UserDto;
import com.naukma.cleaning.services.authenticationService.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class SignUpVC {
    private final AuthenticationService authenticationService;

    @GetMapping("/signUp")
    public String showForm() {
        return "signup-form";
    }

    @Operation(summary = "Add user", description = "Add user")
    @PostMapping("/signUp/success")
    public String addUser(@ModelAttribute @Valid UserDto userDto) {
        authenticationService.register(userDto.getName(),userDto.getEmail(),userDto.getPassword());
        return "index";
    }
}
