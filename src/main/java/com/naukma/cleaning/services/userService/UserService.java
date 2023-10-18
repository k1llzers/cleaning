package com.naukma.cleaning.services.userService;

import com.naukma.cleaning.models.user.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto editUser(UserDto userDto);
    void deleteUser(long id);
    UserDto getUser(long id);
    UserDto getUserByEmail(String email);
}
