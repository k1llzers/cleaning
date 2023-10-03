package com.naukma.cleaning.services.notificationService;

import com.naukma.cleaning.models.user.Role;
import com.naukma.cleaning.models.user.UserDto;
import com.naukma.cleaning.services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(
        value="email.notification.enabled",
        havingValue = "true",
        matchIfMissing = true)
public class EmailNotificationServiceImpl implements NotificationService {
    private UserService userService;

    @Autowired
    public EmailNotificationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void notify(UserDto userDto) {

    }

    @Override
    public void notifyAllUsers() {

    }

    @Override
    public void notifyUsersByRole(Role role) {

    }
}
