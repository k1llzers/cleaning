package com.naukma.cleaning.viewControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


@Controller
public class LoginVC {

   @GetMapping("/login")
    public String showLoginForm(@RequestParam(name = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", true);
        }
        return "login";
    }
}
