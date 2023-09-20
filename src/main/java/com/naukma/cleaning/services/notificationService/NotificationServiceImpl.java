package com.naukma.cleaning.services.notificationService;

import com.naukma.cleaning.models.user.Role;
import com.naukma.cleaning.models.user.User;
import com.naukma.cleaning.services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationServiceImpl implements NotificationService {
    private UserService userService;

    @Autowired
    public NotificationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void notify(User user) {

    }

    @Override
    public void notifyAllUsers() {

    }

    @Override
    public void notifyUsersByRole(Role role) {

    }
}
