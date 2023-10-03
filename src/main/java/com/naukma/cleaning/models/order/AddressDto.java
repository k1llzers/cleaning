package com.naukma.cleaning.models.order;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class AddressDto {
    private long id;
    private String city;
    private String street;
    private String houseNumber;
    private int flatNumber;
}
