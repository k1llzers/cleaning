package com.naukma.cleaning.models.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private long id;
    private String name;
    private String password;
    private String email;
    private Role role;
}
