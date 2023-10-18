package com.naukma.cleaning.services.addressService;

import com.naukma.cleaning.models.order.Address;
import com.naukma.cleaning.models.user.User;

import java.util.List;

public interface AddressService {
    void createAddress(User user, Address address);
    void editAddress(Address address);
    void deleteAddress(long id);
    Address getAddress(long id);
    List<Address> getUserAddresses(User user);
}
