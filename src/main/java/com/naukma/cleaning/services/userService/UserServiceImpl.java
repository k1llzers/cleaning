package com.naukma.cleaning.services.userService;

import com.naukma.cleaning.dao.UserDao;
import com.naukma.cleaning.models.user.UserDto;
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
    public void createUser(UserDto userDto) {
    }

    @Override
    public void editUser(UserDto userDto) {

    }

    @Override
    public void deleteUser(long id) {

    }

    @Override
    public UserDto getUser(long id) {
        return null;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return null;
    }
}
