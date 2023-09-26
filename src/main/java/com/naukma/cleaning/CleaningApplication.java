package com.naukma.cleaning;

import com.naukma.cleaning.services.loggingService.LoggingService;
import com.naukma.cleaning.services.notificationService.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CleaningApplication {

    //@Autowired
    //NotificationService notificationService;

    //@Autowired
    //LoggingService loggingService;

    public static void main(String[] args) {
        SpringApplication.run(CleaningApplication.class, args);
        System.out.println("It's alive!");
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
