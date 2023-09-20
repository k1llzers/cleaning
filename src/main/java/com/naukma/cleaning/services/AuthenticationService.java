package com.naukma.cleaning.services;

import com.naukma.cleaning.models.user.User;

public interface AuthenticationService {
    void register(String name,String email, String password);
    void login(String email, String password);
}
