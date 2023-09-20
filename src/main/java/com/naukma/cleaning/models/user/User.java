package com.naukma.cleaning.models.user;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class User {
    private long id;
    @NonNull
    private String name;
    @NonNull
    private String password;
    @NonNull
    private String email;
    private Role role;
}
