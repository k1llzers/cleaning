package com.naukma.cleaning.services;

import com.naukma.cleaning.models.user.Role;
import com.naukma.cleaning.models.user.User;

public interface NotificationService {
    void notify(User user);
    void notifyAllUsers();
    void notifyUsersByRole(Role role);
}
