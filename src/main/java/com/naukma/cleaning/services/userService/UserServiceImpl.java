package com.naukma.cleaning.services.userService;

import com.naukma.cleaning.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder encoder;
    @Override
    public void createUser(User user) {

    }

    @Override
    public void editUser(User user) {

    }

    @Override
    public void deleteUser(long id) {

    }

    @Override
    public User getUser(long id) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }
}
