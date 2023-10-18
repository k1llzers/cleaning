package com.naukma.cleaning.models.user;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class User {
    private long id;
    @NonNull
    @NotBlank
    @NotNull(message = "Name cannot be null")
    private String name;
    @NonNull
    @NotBlank
    @Size(min = 12, message = "Password is too short!")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).+$", message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit")
    private String password;
    @NonNull
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;
    @NonNull
    @NotNull(message = "Role cannot be null")
    private Role role;
}
