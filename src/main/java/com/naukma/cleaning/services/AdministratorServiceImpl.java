package com.naukma.cleaning.services;

import com.naukma.cleaning.models.user.Role;
import com.naukma.cleaning.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl implements AdministratorService{
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void createEmployee(String name, String email, String password) {
        User employee = new User(name, password, email);
        employee.setRole(Role.Employee);
        userService.createUser(employee);
    }

    @Override
    public void editEmployee(User user) {
        userService.editUser(user);
    }

    @Override
    public void deleteEmployee(long id) {
        userService.deleteUser(id);
    }

    @Override
    public void createAdmin(String name, String email, String password) {
        User admin = new User(name, password, email);
        admin.setRole(Role.Admin);
        userService.createUser(admin);
    }
}
