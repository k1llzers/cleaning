package com.naukma.cleaning.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class AddressEntity {
    @Id
    @GeneratedValue
    private long id;
    private String city;
    private String street;
    private String houseNumber;
    private int flatNumber;
}
