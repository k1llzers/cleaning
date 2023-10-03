package com.naukma.cleaning.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "discount")
public class DiscountEntity {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private double discountPercent;
}
