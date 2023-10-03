package com.naukma.cleaning.dao.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressEntity {
    @Id
    @GeneratedValue
    private long id;
    private String city;
    private String street;
    private String houseNumber;
    private int flatNumber;
}
