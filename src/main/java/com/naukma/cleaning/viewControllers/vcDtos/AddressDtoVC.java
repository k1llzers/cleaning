package com.naukma.cleaning.viewControllers.vcDtos;

import com.naukma.cleaning.models.dtos.AddressDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDtoVC extends AddressDto{
    private boolean canEdit;
    private boolean canDelete;

    public AddressDtoVC(AddressDto addressDto){
        setId(addressDto.getId());
        setCity(addressDto.getCity());
        setStreet(addressDto.getStreet());
        setHouseNumber(addressDto.getHouseNumber());
        setFlatNumber(addressDto.getFlatNumber());
    }
}
