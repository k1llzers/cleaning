package com.naukma.cleaning.services.authenticationService;

import com.naukma.cleaning.models.user.Role;
import com.naukma.cleaning.models.user.UserDto;
import com.naukma.cleaning.services.userService.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    private UserService userService;

    public AuthenticationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void register(String name,String email, String password) {
        UserDto userDto = new UserDto(name, password, email);
        userDto.setRole(Role.User);
        userService.createUser(userDto);
    }

    @Override
    public void login(String email, String password) {
        userService.getUserByEmail(email).getPassword().equals(password);
    }
}
