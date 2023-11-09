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
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String houseNumber;
    @Column(nullable = true)
    private String flatNumber;
    @ManyToOne
    @JoinColumn(name="cleaning_user_fk", nullable=false)
    private UserEntity userEntity;
}
