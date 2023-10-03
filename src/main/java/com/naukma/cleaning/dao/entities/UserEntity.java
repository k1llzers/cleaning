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
@Table(name = "cleaning_user")
public class UserEntity {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_addresses")
    private List<AddressEntity> addressList;
}
