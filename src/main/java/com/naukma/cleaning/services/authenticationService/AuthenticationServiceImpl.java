package com.naukma.cleaning.services.authenticationService;

import com.naukma.cleaning.models.user.Role;
import com.naukma.cleaning.models.user.User;
import com.naukma.cleaning.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    private UserService userService;

    public AuthenticationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void register(String name,String email, String password) {
        User user = new User(name, password, email);
        user.setRole(Role.User);
        userService.createUser(user);
    }

    @Override
    public void login(String email, String password) {
        userService.getUserByEmail(email).getPassword().equals(password);
    }
}
