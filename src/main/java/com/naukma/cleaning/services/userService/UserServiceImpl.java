package com.naukma.cleaning.services.userService;

import com.naukma.cleaning.dao.UserDao;
import com.naukma.cleaning.dao.entities.UserEntity;
import com.naukma.cleaning.models.dtos.UserDto;
import com.naukma.cleaning.models.user.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder encoder;
    private final UserDao userDao;
    private final ModelMapper modelMapper;
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User createUser(User user) {
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
        if(userByEmail == null){
            return null;
        }
        return modelMapper.map(userByEmail, User.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return modelMapper.map(createUser(user), UserDto.class);
    }

    @Override
    public UserDto editUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return modelMapper.map(editUser(user), UserDto.class);
    }

    @Override
    public UserDto getUserDto(long id) {
        return modelMapper.map(getUser(id), UserDto.class);
    }

    @Override
    public UserDto getUserDtoByEmail(String email) {
        return modelMapper.map(getUserByEmail(email), UserDto.class);
    }
}
