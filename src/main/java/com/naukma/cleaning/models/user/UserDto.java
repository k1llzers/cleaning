package com.naukma.cleaning.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDto {
    private long id;
    @NonNull
    private String name;
    @NonNull
    private String password;
    @NonNull
    private String email;
    @NonNull
    private Role role;
}
