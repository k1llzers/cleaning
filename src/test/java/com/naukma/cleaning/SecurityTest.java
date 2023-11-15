package com.naukma.cleaning;

import com.naukma.cleaning.models.dtos.AddressDto;
import com.naukma.cleaning.services.addressService.AddressService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @MockBean
    private static AddressService addressService;
    private static AddressDto addressDto;

    @BeforeAll
    static void init(){
        addressDto = new AddressDto();
        addressDto.setId(1);
        addressDto.setCity("Kyiv");
        addressDto.setStreet("Obolonstyi avenue");
        addressDto.setHouseNumber("4-a");
        addressDto.setFlatNumber("11");
    }

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @WithAnonymousUser
    @Test
    void testGetAddresses_ForAnonymousUser_Expect_401Unauthorized() throws Exception {
        this.mockMvc.perform(get("/addresses/1")).andExpect(status().isUnauthorized());
    }

    @WithMockUser(username = "user",roles = {"User"})
    @Test
    void testGetUserAddresses_ForUser_Expect_403Forbidden() throws Exception {
        Mockito.when(addressService.getAddressesByUserId(1l)).thenReturn(List.of(addressDto));
        this.mockMvc.perform(get("/addresses/by-user/1")).andExpect(status().isForbidden());
    }

    @WithMockUser(username = "employee",roles = {"Employee"})
    @Test
    void testDeleteAddress_ForEmployee_Expect_403Forbidden() throws Exception {
        Mockito.doNothing().when(addressService).deleteAddress(Mockito.anyInt());
        this.mockMvc.perform(delete("/addresses/1")).andExpect(status().isForbidden());
    }

    @WithMockUser(username = "user",roles = {"User"})
    @Test
    void testGetAddresses_ForUser_Expect_200Ok() throws Exception {
        Mockito.when(addressService.getAddressDto(1l)).thenReturn(addressDto);
        this.mockMvc.perform(get("/addresses/1")).andExpect(status().isOk());
    }

    @WithMockUser(username = "admin",roles = {"Admin"})
    @Test
    void testGetUserAddresses_ForAdmin_Expect_200Ok() throws Exception {
        Mockito.when(addressService.getAddressesByUserId(1l)).thenReturn(List.of(addressDto));
        this.mockMvc.perform(get("/addresses/by-user/1")).andExpect(status().isOk());
    }

    @WithMockUser(username = "user",roles = {"User"})
    @Test
    void testDeleteAddress_ForAdmin_Expect_200Ok() throws Exception {
        Mockito.doNothing().when(addressService).deleteAddress(Mockito.anyInt());
        this.mockMvc.perform(delete("/addresses/1")).andExpect(status().isOk());
    }
}

