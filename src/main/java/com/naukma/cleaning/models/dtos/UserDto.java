package com.naukma.cleaning.models.dtos;

import com.naukma.cleaning.models.user.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDto {
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
