package com.naukma.cleaning.services.userService;

import com.naukma.cleaning.models.dtos.UserDto;
import com.naukma.cleaning.models.user.User;

public interface UserService {
    User createUser(User user);
    User editUser(User user);
    void deleteUser(long id);
    User getUser(long id);
    User getUserByEmail(String email);
    UserDto createUser(UserDto userDto);
    UserDto editUser(UserDto userDto);
    UserDto getUserDto(long id);
    UserDto getUserDtoByEmail(String email);
}
