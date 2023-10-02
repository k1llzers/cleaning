package com.naukma.cleaning.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class DiscountEntity {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private double discountPercent;
}
