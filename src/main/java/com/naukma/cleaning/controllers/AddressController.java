package com.naukma.cleaning.controllers;

import com.naukma.cleaning.models.dtos.AddressDto;
import com.naukma.cleaning.models.dtos.UserDto;
import com.naukma.cleaning.services.addressService.AddressService;
import com.naukma.cleaning.services.commentService.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    public final AddressService addressService;

    @PostMapping("/{userId}")
    public void createAddress(@PathVariable long userId, @RequestBody AddressDto addressDto) {
        addressService.createAddress(userId, addressDto);
    }

    @PutMapping()
    public void editAddress(@RequestBody AddressDto addressDto) {
        addressService.editAddress(addressDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable long id) {
        addressService.deleteAddress(id);
    }

    @GetMapping("/{id}")
    public AddressDto getAddress(@PathVariable long id) {
        return addressService.getAddressDto(id);
    }

    @GetMapping()
    public List<AddressDto> getUserAddresses(@RequestBody UserDto userDto) {
        return addressService.getUserAddresses(userDto);
    }

}
