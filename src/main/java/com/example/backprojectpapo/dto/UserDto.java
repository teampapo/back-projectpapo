package com.example.backprojectpapo.dto;

import com.example.backprojectpapo.model.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserDto{
    @NotNull
    @NotBlank
    protected String email;
    private String password;
    @NotNull
    @NotBlank
    private Role role;
    @NotNull
    @NotBlank
    private Boolean isActive;
}
