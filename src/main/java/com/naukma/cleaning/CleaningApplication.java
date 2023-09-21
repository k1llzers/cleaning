package com.naukma.cleaning;

import com.naukma.cleaning.models.order.Order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CleaningApplication {

    public static void main(String[] args) {
        SpringApplication.run(CleaningApplication.class, args);
        System.out.println("It's alive!");
    }

}
