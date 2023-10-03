package com.naukma.cleaning.dao.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "commercial_proposal")
public class CommercialProposalEntity {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @Column(length = 1000)
    private String description;
    private double price;
}
