package com.naukma.cleaning.services.userService;

import com.naukma.cleaning.models.user.User;

public interface UserService {
    User createUser(User user);
    User editUser(User user);
    void deleteUser(long id);
    User getUser(long id);
    User getUserByEmail(String email);
}
