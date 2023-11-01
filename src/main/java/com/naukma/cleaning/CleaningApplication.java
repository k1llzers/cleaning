package com.naukma.cleaning;

import com.naukma.cleaning.models.dtos.AddressDto;
import com.naukma.cleaning.models.dtos.OrderDto;
import com.naukma.cleaning.models.dtos.UserDto;
import com.naukma.cleaning.models.order.Address;
import com.naukma.cleaning.models.order.Comment;
import com.naukma.cleaning.models.order.CommercialProposal;
import com.naukma.cleaning.models.user.Role;
import com.naukma.cleaning.models.user.User;
import com.naukma.cleaning.services.addressService.AddressService;
import com.naukma.cleaning.services.orderService.OrderService;
import com.naukma.cleaning.services.proposalService.CommercialProposalServiceImpl;
import com.naukma.cleaning.services.userService.UserService;
import com.naukma.cleaning.services.userService.UserServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.lang.reflect.Array;
import java.time.LocalDateTime;

import java.time.LocalDateTime;
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
        user1.setPassword("qwerty");
        user1.setRole(Role.User);
        User user2 = new User();
        user2.setId(2);
        user2.setName("Michael");
        user2.setEmail("google2@gmail.com");
        user2.setPassword("qwerty123");
        user2.setRole(Role.User);

        UserServiceImpl userService = (UserServiceImpl) context.getBean("userServiceImpl");
        userService.createUser(user1);
        userService.createUser(user2);


        var userDTO = userService.createUser(new UserDto("name 0", "p@SsW0rd", "ema@e.ukma", Role.User)); 
        var user = userService.getUser(3);
        var addressservice = (AddressService)context.getBean("addressServiceImpl");
        var address = new Address();
        address.setId(1);
        address.setCity("Kyiv");
        address.setStreet("KPI");
        address.setHouseNumber("8d");
        address.setFlatNumber(7);
        addressservice.createAddress(user, address);
        userService.createUser(new UserDto("name 1e", "p@SsW1rd", "ex@e.edu", Role.Employee));	
        var exec = userService.getUser(4);
        var execs = new HashSet<User>();
        execs.add(exec);
        OrderService orderService = (OrderService)context.getBean("orderServiceImpl");
        orderService.createOrder(new OrderDto(1, 405.0, LocalDateTime.now(), LocalDateTime.now().plusHours(1), 
            user, execs, null, address, 
            com.naukma.cleaning.models.order.Status.NOT_VERIFIED, new HashSet<CommercialProposal>()));
        System.out.println(orderService.getOrder(1).toString());
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
