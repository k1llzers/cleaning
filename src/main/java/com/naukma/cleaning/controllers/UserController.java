package com.naukma.cleaning.controllers;

import com.naukma.cleaning.models.user.User;
import com.naukma.cleaning.services.userService.UserService;
import com.naukma.cleaning.utils.exceptions.EmailDuplicateException;
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

import java.time.LocalDateTime;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        throw new EmailDuplicateException();
//        return userService.getUser(id);
    }

    @GetMapping("/by-email")
    public User getUserByEmail(@RequestParam String email){
        RestTemplate restTemplate = new RestTemplate();
        String resource = "http://worldtimeapi.org/api/timezone/Europe/Kyiv";
        ResponseEntity<String> time = restTemplate.getForEntity(resource, String.class);
        log.info("time from api:" + time.getBody());
        return userService.getUserByEmail(email);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PostMapping
    public User addUser(@RequestBody @Valid User user){
        return userService.createUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody @Valid User user){
        return userService.editUser(user);
    }

    @ExceptionHandler({EmailDuplicateException.class})
    public void handleException(Exception ex){
        log.warn("Excep");
    }
}
