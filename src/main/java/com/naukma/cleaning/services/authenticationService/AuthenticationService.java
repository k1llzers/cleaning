package com.naukma.cleaning.services.authenticationService;

import com.naukma.cleaning.models.dtos.UserDto;

public interface AuthenticationService {
    UserDto register(String name, String email, String password);
    void login(String email, String password);
}
