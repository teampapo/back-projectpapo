package com.example.backprojectpapo.dto.request;

import com.example.backprojectpapo.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AuthCustomerRequestDTO extends UserDto {
    @Size(max = 50,message = "\"surname\" field should be no more than 50 characters long")
    private String surname;
    @Size(max = 50,message = "\"name\" field should be no more than 50 characters long")
    private String name;
    @Size(max = 50,message = "\"patronymic\" field should be no more than 50 characters long")
    private String patronymic;
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    @Size(max = 20,message = "\"phoneNumber\" field should be no more than 20 characters long")
    @NotBlank(message="The \"phoneNumber\" field must be filled in")
    private String phoneNumber;
    @Size(max = 250, message = "\"addInfo\" field should be no more than 20 characters long")
    private String addInfo;
    @Pattern(regexp = "[0-9]+", message = "Invalid code format")
    @Size(min = 6,max = 6,message = "code must contain 6 characters")
    @NotBlank(message = "Code cannot be null or empty")
    private String code;
}
