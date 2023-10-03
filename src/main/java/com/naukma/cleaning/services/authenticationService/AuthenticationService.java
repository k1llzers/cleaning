package com.naukma.cleaning.services.authenticationService;

public interface AuthenticationService {
    void register(String name,String email, String password);
    void login(String email, String password);
}
