package com.example.backprojectpapo.dto;

import com.example.backprojectpapo.model.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto{
    @NotNull
    private String email;

    private String password;

    @NotNull
    private Role role;

    @NotNull
    private Boolean isActive;
}
