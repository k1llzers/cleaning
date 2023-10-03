package com.naukma.cleaning;

import com.naukma.cleaning.models.user.Role;
import com.naukma.cleaning.models.user.UserDto;
import com.naukma.cleaning.services.loggingService.LoggingService;
import com.naukma.cleaning.services.notificationService.NotificationService;
import com.naukma.cleaning.services.userService.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@SpringBootApplication
public class CleaningApplication {

    //@Autowired
    //NotificationService notificationService;

    //@Autowired
    //LoggingService loggingService;

    public static void main(String[] args) {
        var context = SpringApplication.run(CleaningApplication.class, args);
        System.out.println("It's alive!");
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
