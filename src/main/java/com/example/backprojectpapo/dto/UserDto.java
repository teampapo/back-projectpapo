package com.example.backprojectpapo.dto;

import com.example.backprojectpapo.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Integer id;
    @NotBlank
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("role")
    private Role role;
    @NotNull
    @JsonProperty("isActive")
    private Boolean isActive = true;
}
