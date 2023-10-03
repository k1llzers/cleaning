package com.naukma.cleaning.services.userService;

import com.naukma.cleaning.dao.UserDao;
import com.naukma.cleaning.dao.entities.UserEntity;
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
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        userDao.save(userEntity);
    }

    @Override
    public void editUser(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userDao.save(userEntity);
    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteById(id);
    }

    @Override
    public UserDto getUser(long id) {
        UserEntity userById = userDao.getReferenceById(id);
        return modelMapper.map(userById,UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UserEntity userByEmail = userDao.findUserEntityByEmail(email);
        return modelMapper.map(userByEmail,UserDto.class);
    }
}
