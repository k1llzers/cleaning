package com.naukma.cleaning.services.authenticationService;

import com.naukma.cleaning.models.dtos.UserDto;
import com.naukma.cleaning.models.user.Role;
import com.naukma.cleaning.models.user.User;
import com.naukma.cleaning.services.userService.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    private final UserService userService;
    private final ModelMapper modelMapper;

    public AuthenticationServiceImpl(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.modelMapper = mapper;
    }

    @Override
    public UserDto register(String name,String email, String password) {
        User user = userService.createUser(new User(name, password, email,Role.User));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public void login(String email, String password) {
        userService.getUserByEmail(email).getPassword().equals(password);
    }
}
