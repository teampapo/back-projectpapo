package com.example.backprojectpapo.dto.request;

import com.example.backprojectpapo.dto.UserDto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AuthAggregatorSpecialistRequestDTO extends UserDto {
    @NotBlank
    private String surname;
    @NotBlank
    private String name;
    private String patronymic;
    @NotBlank
    private String department;
    @NotBlank
    private String position;
    @NotBlank
    private String phoneNumber;
    private String addInfo;
}
