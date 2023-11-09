package com.naukma.cleaning;

import com.naukma.cleaning.models.dtos.CommentDto;
import com.naukma.cleaning.models.dtos.OrderDto;
import com.naukma.cleaning.models.dtos.UserDto;
import com.naukma.cleaning.models.order.Address;
import com.naukma.cleaning.models.order.CommercialProposal;
import com.naukma.cleaning.models.user.Role;
import com.naukma.cleaning.models.user.User;
import com.naukma.cleaning.services.addressService.AddressService;
import com.naukma.cleaning.services.commentService.CommentService;
import com.naukma.cleaning.services.orderService.OrderService;
import com.naukma.cleaning.services.proposalService.CommercialProposalServiceImpl;
import com.naukma.cleaning.services.userService.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.HashSet;
import java.time.LocalDateTime;

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
        for(int i = 2; i <= 6; i++) {
            CommercialProposal prop1 = new CommercialProposal();
            prop1.setId(i);
            prop1.setName("Super proposal" + i);
            prop1.setPrice(i * 100.0);
            prop1.setDescription("This is description of proposal" + i);
            ((CommercialProposalServiceImpl) context.getBean("commercialProposalServiceImpl")).createCommercialProposal(prop1);
        }

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
        User user3 = new User();
        user3.setId(3);
        user3.setName("admin");
        user3.setEmail("admin");
        user3.setPassword("admin");
        user3.setRole(Role.Admin);
        UserServiceImpl userService = (UserServiceImpl) context.getBean("userServiceImpl");
        userService.createUser(user1);
        userService.createUser(user2);
        userService.createUser(user3);


        var userDTO = userService.createUser(new UserDto("name 0", "p@SsW0rd", "ema@e.ukma", Role.User));
        var user = userService.getUser(2);
        var addressservice = (AddressService)context.getBean("addressServiceImpl");
        var address = new Address();
        address.setId(1);
        address.setCity("Kyiv");
        address.setStreet("KPI");
        address.setHouseNumber("8d");
        address.setFlatNumber("7");
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
        CommentService commentService = (CommentService)context.getBean("commentServiceImpl");
        CommentDto comment = new CommentDto(1, "Text", LocalDateTime.now(), 5);
        //commentService.createComment(1l, comment);
        System.out.println(orderService.getOrder(1).toString());
        comment.setText("ayayay");
        //commentService.editComment(comment);
        System.out.println(orderService.getOrder(1).toString());
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
