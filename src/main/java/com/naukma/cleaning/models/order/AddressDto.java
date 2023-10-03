package com.naukma.cleaning.models.order;


import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class AddressDto {
    private long id;
    @NonNull
    private String city;
    @NonNull
    private String street;
    @NonNull
    private String houseNumber;
    //TODO: nullable?
    @NonNull
    private int flatNumber;
}
