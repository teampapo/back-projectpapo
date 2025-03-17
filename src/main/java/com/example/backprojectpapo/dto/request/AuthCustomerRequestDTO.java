package com.example.backprojectpapo.dto.request;

import com.example.backprojectpapo.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AuthCustomerRequestDTO extends UserDto {
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("name")
    private String name;
    @JsonProperty("patronymic")
    private String patronymic;
    @NotBlank
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("addInfo")
    private String addInfo;
}
