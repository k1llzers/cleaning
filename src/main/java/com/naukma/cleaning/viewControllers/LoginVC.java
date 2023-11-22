package com.naukma.cleaning.viewControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginVC {

   @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
