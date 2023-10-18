package com.naukma.cleaning.services.addressService;

import com.naukma.cleaning.models.dtos.AddressDto;
import com.naukma.cleaning.models.dtos.UserDto;
import com.naukma.cleaning.models.order.Address;
import com.naukma.cleaning.models.user.User;

import java.util.List;

public interface AddressService {
    void createAddress(User user, Address address);
    void createAddress(long userId, AddressDto addressDto);
    void editAddress(AddressDto addressDto);
    void editAddress(Address address);
    void deleteAddress(long id);
    Address getAddress(long id);
    AddressDto getAddressDto(long id);
    List<Address> getUserAddresses(User user);
    List<AddressDto> getUserAddresses(UserDto userDto);
}
