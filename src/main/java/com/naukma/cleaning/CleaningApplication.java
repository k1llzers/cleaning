package com.naukma.cleaning;

import com.naukma.cleaning.models.order.CommercialProposal;
import com.naukma.cleaning.models.user.Role;
import com.naukma.cleaning.models.user.User;
import com.naukma.cleaning.services.proposalService.CommercialProposalServiceImpl;
import com.naukma.cleaning.services.userService.UserServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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
        CommercialProposal prop = new CommercialProposal();
        prop.setId(1);
        prop.setName("Super proposal");
        prop.setPrice(100.0);
        prop.setDescription("This is description of proposal");
        ((CommercialProposalServiceImpl)context.getBean("commercialProposalServiceImpl")).createCommercialProposal(prop);

        User user1 = new User();
        user1.setId(1);
        user1.setName("Jonathan");
        user1.setEmail("google@gmail.com");
        user1.setPassword("asjhubulichauihcbl");
        user1.setRole(Role.User);
        User user2 = new User();
        user2.setId(2);
        user2.setName("Michael");
        user2.setEmail("google@gmail.com");
        user2.setPassword("asjhubulichauihcbl");
        user2.setRole(Role.User);

        UserServiceImpl userService = (UserServiceImpl) context.getBean("userServiceImpl");
        userService.createUser(user1);
        userService.createUser(user2);

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
