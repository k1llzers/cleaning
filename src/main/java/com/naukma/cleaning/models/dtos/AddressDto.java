package com.naukma.cleaning.models.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDto {
    private long id;
    private String city;
    private String street;
    private String houseNumber;
    private int flatNumber;
}
