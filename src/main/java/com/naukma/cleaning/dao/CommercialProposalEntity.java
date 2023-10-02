package com.naukma.cleaning.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class CommercialProposalEntity {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @Column(length = 1000)
    private String description;
    private double price;
}
