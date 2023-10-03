package com.naukma.cleaning.services.addressService;

import com.naukma.cleaning.models.order.AddressDto;
import com.naukma.cleaning.models.user.UserDto;

import java.util.List;

public interface AddressService {
    void createAddress(UserDto userDto, AddressDto addressDto);
    void editAddress(AddressDto addressDto);
    void deleteAddress(long id);
    AddressDto getAddress(long id);
    List<AddressDto> getUserAddresses(UserDto userDto);
}
