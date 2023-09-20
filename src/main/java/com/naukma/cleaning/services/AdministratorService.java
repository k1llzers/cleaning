package com.naukma.cleaning.services;

import com.naukma.cleaning.models.user.User;

public interface AdministratorService {
    void createEmployee(User user);
    void editEmployee(long id);
    void deleteEmployee(long id);
    void createAdmin(User user);
}
