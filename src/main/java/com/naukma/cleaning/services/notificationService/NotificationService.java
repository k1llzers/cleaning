package com.naukma.cleaning.services.notificationService;

import com.naukma.cleaning.models.user.Role;
import com.naukma.cleaning.models.user.UserDto;

public interface NotificationService {
    void notify(UserDto userDto);
    void notifyAllUsers();
    void notifyUsersByRole(Role role);
}
