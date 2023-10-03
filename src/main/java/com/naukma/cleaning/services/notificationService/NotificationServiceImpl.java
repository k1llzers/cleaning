package com.naukma.cleaning.services.notificationService;

import com.naukma.cleaning.models.user.Role;
import com.naukma.cleaning.models.user.UserDto;
import com.naukma.cleaning.services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(EmailNotificationServiceImpl.class)
public class NotificationServiceImpl implements NotificationService {
    private UserService userService;

    @Autowired
    public NotificationServiceImpl(UserService userService) {
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
