package com.naukma.cleaning.services.userService;

import com.naukma.cleaning.models.user.UserDto;

public interface UserService {
    void createUser(UserDto userDto);
    void editUser(UserDto userDto);
    void deleteUser(long id);
    UserDto getUser(long id);
    UserDto getUserByEmail(String email);
}
