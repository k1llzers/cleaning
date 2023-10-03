package com.naukma.cleaning.dao.entities;

import com.naukma.cleaning.models.user.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_addresses")
    @Column(nullable = false)
    private List<AddressEntity> addressList;
}
