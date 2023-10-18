package com.naukma.cleaning.services.administratorService;

import com.naukma.cleaning.models.user.User;

public interface AdministratorService {
    void createEmployee(String name,String email, String password);
    void editEmployee(User user);
    void deleteEmployee(long id);
    void createAdmin(String name, String email, String password);
}
