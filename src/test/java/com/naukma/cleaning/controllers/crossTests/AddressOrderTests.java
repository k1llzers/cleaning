package com.naukma.cleaning.controllers.crossTests;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.naukma.cleaning.controllers.AddressController;
import com.naukma.cleaning.controllers.OrderController;
import com.naukma.cleaning.controllers.UserController;
import com.naukma.cleaning.models.dtos.AddressDto;
import com.naukma.cleaning.models.dtos.OrderDto;
import com.naukma.cleaning.models.dtos.UserDto;
import com.naukma.cleaning.models.order.Address;
import com.naukma.cleaning.models.order.CommercialProposal;
import com.naukma.cleaning.models.user.Role;
import com.naukma.cleaning.models.user.User;
import com.naukma.cleaning.services.addressService.AddressService;
import com.naukma.cleaning.services.orderService.OrderService;
import com.naukma.cleaning.services.userService.UserService;

@SpringBootTest
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class AddressOrderTests {

    @BeforeAll
    static void setup(@Autowired UserService userService, @Autowired AddressService addressService, @Autowired UserController userController, @Autowired OrderService orderService){
        var user = new UserDto();
        user.setName("Johnny");
        user.setPassword("12345678");
        user.setEmail("em@ai.ll");
        userController.addUser(user);

        var uid = userController.getUserByEmail("em@ai.ll").getId();
        
        var address = new Address();
        address.setId(1);
        address.setCity("Kyiv");
        address.setStreet("Khreshchatyk");
        address.setHouseNumber("1");
        address.setFlatNumber("1");

        User userU = userService.getUser(uid);
        addressService.createAddress(userU, address);

        userService.createUser(new UserDto("name 1e", "p@SsW1rd", "ex@e.edu", Role.Employee));
        userService.createUser(new UserDto("name 2e", "p@SsW12rd", "ex2@e.edu", Role.Employee));
        var exec = userService.getUser(2);

        var execs = new HashSet<User>();
        execs.add(exec);
        
        orderService.createOrder(new OrderDto(1l, 405.0, LocalDateTime.now(), LocalDateTime.now().plusHours(1),
            userU, execs, null, address,
            com.naukma.cleaning.models.order.Status.NOT_VERIFIED, new HashSet<CommercialProposal>()));
    }

    @Test
    @WithMockUser(username = "em@ai.ll", roles = "User")
    @Order(1)
    public void addGetAddress(@Autowired OrderController orderController, @Autowired AddressController addressController) {
        var order = orderController.getOrder(1l);
        assert(order != null);

        var address = order.getAddress();
        assert(address != null);

        try{
            addressController.deleteAddress(address.getId());
            assert(false);
        } catch (Exception e) {}

        var addressDTO = addressController.getAddress(address.getId());
        addressDTO.setCity("Zhytomyr");
        addressController.editAddress(addressDTO);

        try{
            addressController.deleteAddress(address.getId());
            assert(false);
        } catch (Exception e) {}

        try{
            order.setOrderStatus(com.naukma.cleaning.models.order.Status.VERIFIED);
            orderController.editOrder(order);
            assert(false);
        } catch (Exception e) {}
    }

    @Test
    @WithMockUser(username = "ex@e.edu", roles = "Employee")
    @Order(2)
    public void editOrderStatusEmployee(@Autowired OrderController orderController){
        var order = orderController.getOrder(1l);
        order.setOrderStatus(com.naukma.cleaning.models.order.Status.VERIFIED);
        orderController.editOrder(order);
    }
    
    @Test
    @WithMockUser(username = "ex2@e.edu", roles = "Employee")
    @Order(3)
    public void editOrderStatusOtherEmployee(@Autowired OrderController orderController){
        try {
            var order = orderController.getOrder(1l);
            order.setOrderStatus(com.naukma.cleaning.models.order.Status.NOT_STARTED);
            orderController.editOrder(order);
            assert(false);
        } catch (Exception e) {}
    }

    @Test
    @WithMockUser(username = "em@ai.ll", roles = "User")
    @Order(4)
    public void editVerifiedOrderAddress(@Autowired OrderController orderController, @Autowired AddressController addressController){
        var order = orderController.getOrder(1l);
        assert(order != null);

        var address = order.getAddress();
        assert(address != null);

        try{
            addressController.deleteAddress(address.getId());
            assert(false);
        } catch (Exception e) {}

        var addressDTO = addressController.getAddress(address.getId());

        try{
            addressDTO.setCity("Lviv");
            addressController.editAddress(addressDTO);
            assert(false);
        }catch (Exception e){}
    }
}
