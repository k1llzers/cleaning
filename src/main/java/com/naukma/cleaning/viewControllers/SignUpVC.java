package com.naukma.cleaning.viewControllers;

import com.naukma.cleaning.models.dtos.UserDto;
import com.naukma.cleaning.services.authenticationService.AuthenticationService;
import com.naukma.cleaning.services.userService.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class SignUpVC {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @GetMapping("/signUp")
    public String showForm() {
        return "signup-form";
    }

    @Operation(summary = "Add user", description = "Add user")
    @PostMapping("/signUp/success")
    public String addUser(@ModelAttribute UserDto userDto) {
        String email = userDto.getEmail();
        Validator validator = Validation.buildDefaultValidatorFactory().usingContext().getValidator();
        if(!validator.validateProperty(userDto, "password").isEmpty()){
            return "redirect:/signUp?error=pass";
        }
        if (userService.getUserByEmail(email) != null) {
            return "redirect:/signUp?error=email";
        }
        authenticationService.register(userDto.getName(),userDto.getEmail(),userDto.getPassword());
        return "redirect:/login?registered";
    }
}
