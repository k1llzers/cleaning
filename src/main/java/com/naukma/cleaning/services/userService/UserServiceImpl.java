package com.naukma.cleaning.services.userService;

import com.naukma.cleaning.dao.UserDao;
import com.naukma.cleaning.dao.entities.UserEntity;
import com.naukma.cleaning.models.user.User;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder encoder;
    private UserDao userDao;
    private ModelMapper modelMapper;
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(PasswordEncoder encoder, UserDao userDao, ModelMapper modelMapper) {
        this.encoder = encoder;
        this.userDao = userDao;
        this.modelMapper = modelMapper;
        MDC.put("param","value");
        LOGGER.error("UserService created!");
        MDC.clear();
    }

    @Override
    public User createUser(User user) {
        MDC.put("NewUserID", String.valueOf(user.getId()));
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        return modelMapper.map(userDao.save(userEntity), User.class);
    }

    @Override
    public User editUser(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        return modelMapper.map(userDao.save(userEntity), User.class);
    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteById(id);
    }

    @Override
    public User getUser(long id) {
        UserEntity userById = userDao.findById(id).get();
        return modelMapper.map(userById, User.class);
    }

    @Override
    public User getUserByEmail(String email) {
        UserEntity userByEmail = userDao.findUserEntityByEmail(email);
        return modelMapper.map(userByEmail, User.class);
    }
}
