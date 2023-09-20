package com.naukma.cleaning.services.userService;

import com.naukma.cleaning.models.user.User;

public interface UserService {
    void createUser(User user);
    void editUser(User user);
    void deleteUser(long id);
    User getUser(long id);
    User getUserByEmail(String email);
}
