package com.naukma.cleaning.services.addressService;

import com.naukma.cleaning.models.order.AddressDto;

public interface AddressService {
    void createAddress(AddressDto addressDto);
    void editAddress(AddressDto addressDto);
    void deleteAddress(long id);
    AddressDto getAddress(long id);
}
