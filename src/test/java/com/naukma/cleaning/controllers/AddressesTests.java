package com.naukma.cleaning.controllers;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.naukma.cleaning.controllers.AddressController;
import com.naukma.cleaning.controllers.UserController;
import com.naukma.cleaning.models.dtos.AddressDto;
import com.naukma.cleaning.models.dtos.UserDto;
import com.naukma.cleaning.models.user.User;

import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
public class AddressesTests {

    @Autowired 
    AddressController addressController;

    @Test
    public void createAddressDto() {
        var addressDto = new AddressDto();
        assert(addressDto != null);
        addressDto.setCity("Kyiv");
        addressDto.setStreet("Khreshchatyk");
        addressDto.setHouseNumber("1");
        addressDto.setFlatNumber("1");
        assert(addressDto.getCity().equals("Kyiv"));
    }

    @BeforeAll
    static void setup(@Autowired UserController userController){
        var user = new UserDto();
        user.setName("John");
        user.setPassword("12345");
        user.setEmail("em@ai.l");

        userController.addUser(user);

        var user2 = userController.getUserByEmail("em@ai.l");
        assert(user2.getName().equals("John"));
    }

    @Test
    @WithMockUser(username = "em@ai.l", roles = "USER")
    @Order(1)
    public void addGetAddress(@Autowired UserController userController) {
        var addressDto = new AddressDto();
        addressDto.setCity("Kyiv");
        addressDto.setStreet("Khreshchatyk");
        addressDto.setHouseNumber("1");
        addressDto.setFlatNumber("1");
        try {
            addressController.createAddress(1000L, addressDto);
            assert(false);
        } catch (Exception e) {}
        var uid = userController.getUserByEmail("em@ai.l").getId();
        addressController.createAddress(uid, addressDto);

        var addressDto2 = addressController.getAddress(uid);
        assert(addressDto2.getCity().equals("Kyiv"));
        assert(addressDto2.getStreet().equals("Khreshchatyk"));
        assert(addressController.getUserAddresses(uid).contains(addressDto2));
        try{
            addressController.getAddress(1000L);
            assert(false);
        } catch (Exception e) {}
    }

    @Test
    @WithMockUser(username = "em@ai.l", roles = "USER")
    @Order(2)
    public void editDeleteAddress(){
        var addressDto = addressController.getAddress(1l);
        assert(addressDto != null);
        addressDto.setCity("Lviv");
        addressDto.setStreet("Shevchenka");
        addressController.editAddress(addressDto);

        var addressDto2 = addressController.getAddress(1l);
        assert(addressDto2.getCity().equals("Lviv"));

        addressController.deleteAddress(1l);
        try{
            addressController.getAddress(1l);
            assert(false);
        } catch (Exception e) {}
    }
    
}
