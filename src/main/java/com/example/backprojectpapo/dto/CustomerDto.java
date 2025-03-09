package com.example.backprojectpapo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CustomerDto extends UserDto{
    private String surname;
    private String name;
    private String patronymic;
    @NotBlank
    private String phoneNumber;
    private String addInfo;
}
