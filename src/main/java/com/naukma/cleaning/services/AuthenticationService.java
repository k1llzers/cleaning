package com.naukma.cleaning.services;

import com.naukma.cleaning.models.user.User;

public interface AuthenticationService {
    void register(User user);
    void login(String email, String password);
}
