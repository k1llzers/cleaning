package com.naukma.cleaning.services.addressService;

import com.naukma.cleaning.models.order.Address;
import com.naukma.cleaning.models.order.Comment;

public interface AddressService {
    void createAddress(Address address);
    void editAddress(Address address);
    void deleteAddress(long id);
    Address getAddress(long id);
}
