package com.example.backprojectpapo.dto;

import com.example.backprojectpapo.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserDto{
    private Integer id;
    @Size(max = 50,message = "\"email\" field should be no more than 50 characters long")
    @NotBlank(message = "Email cannot be null or empty")
    @Email(message = "Email should be valid")
    private String email;
    private String password;
    private Role role;
    private Boolean isActive = true;
}
