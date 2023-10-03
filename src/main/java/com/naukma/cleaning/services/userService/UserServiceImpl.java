package com.naukma.cleaning.services.userService;

import com.naukma.cleaning.dao.UserDao;
import com.naukma.cleaning.models.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder encoder;
    private UserDao userDao;
    private ModelMapper modelMapper;

    public UserServiceImpl(PasswordEncoder encoder, UserDao userDao, ModelMapper modelMapper) {
        this.encoder = encoder;
        this.userDao = userDao;
        this.modelMapper = modelMapper;
    }

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
