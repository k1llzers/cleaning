package com.naukma.cleaning.services;

import com.naukma.cleaning.models.user.User;
import com.naukma.cleaning.services.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userByEmail = userService.getUserByEmail(username);
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder
                = org.springframework.security.core.userdetails.User.withUsername(username);
        userBuilder.password(userByEmail.getPassword());
        userBuilder.authorities("ROLE_" + userByEmail.getRole());
        return userBuilder.build();
    }
}
