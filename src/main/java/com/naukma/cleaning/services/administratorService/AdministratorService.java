package com.naukma.cleaning.services.administratorService;

import com.naukma.cleaning.models.user.UserDto;

public interface AdministratorService {
    void createEmployee(String name,String email, String password);
    void editEmployee(UserDto userDto);
    void deleteEmployee(long id);
    void createAdmin(String name, String email, String password);
}
