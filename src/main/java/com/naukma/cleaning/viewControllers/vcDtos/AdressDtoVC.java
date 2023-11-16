package com.naukma.cleaning.viewControllers.vcDtos;

import com.naukma.cleaning.models.dtos.AddressDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdressDtoVC extends AddressDto{
    private boolean canEdit;
    private boolean canDelete;

    public AdressDtoVC(AddressDto addressDto){
        setId(addressDto.getId());
        setCity(addressDto.getCity());
        setStreet(addressDto.getStreet());
        setHouseNumber(addressDto.getHouseNumber());
        setFlatNumber(addressDto.getFlatNumber());
    }
}
