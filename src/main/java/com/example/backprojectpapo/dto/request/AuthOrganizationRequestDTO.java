package com.example.backprojectpapo.dto.request;


import com.example.backprojectpapo.dto.AddressDTO;
import com.example.backprojectpapo.dto.UserDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AuthOrganizationRequestDTO extends UserDto {
    @Size(max = 150,message = "\"fullName\" field should be no more than 150 characters long")
    @NotBlank(message="\"fullName\" field must be filled in")
    private String fullName;
    @Size(max = 50,message = "\"shortName\" field should be no more than 50 characters long")
    @NotBlank(message="\"shortName\" field must be filled in")
    private String shortName;
    @NotBlank(message="\"inn\" field must be filled in")
    @Size(max = 10,message = "\"inn\" field should be no more than 10 characters long")
    private String inn;
    @NotBlank(message="\"kpp\" field must be filled in")
    @Size(max = 9,message = "\"kpp\" field should be no more than 9 characters long")
    private String kpp;
    @Size(max = 13,message = "\"ogrn\" field should be no more than 13 characters long")
    @NotBlank(message="\"ogrn\" field must be filled in")
    private String ogrn;
    @Size(max = 50,message = "\"responsiblePersonSurname\" field should be no more than 50 characters long")
    @NotBlank(message="\"responsiblePersonSurname\" field must be filled in")
    private String responsiblePersonSurname;
    @Size(max = 50,message = "\"responsiblePersonName\" field should be no more than 50 characters long")
    @NotBlank(message="\"responsiblePersonName\" field must be filled in")
    private String responsiblePersonName;
    @Size(max = 50,message = "\"responsiblePersonPatronymic\" field should be no more than 50 characters long")
    private String responsiblePersonPatronymic;
    @Size(max = 50,message = "\"responsiblePersonEmail\" field should be no more than 50 characters long")
    @NotBlank(message="\"responsiblePersonEmail\" field must be filled in")
    private String responsiblePersonEmail;
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    @Size(max = 20,message = "\"responsiblePersonPhoneNumber\" field should be no more than 20 characters long")
    @NotBlank(message="\"responsiblePersonPhoneNumber\" field must be filled in")
    private String responsiblePersonPhoneNumber;
    @Valid
    @NotNull(message = "Address must be specified")
    private AddressDTO address;
    @Size(max = 250, message = "\"addInfo\" field should be no more than 20 characters long")
    private String addInfo;
    @Pattern(regexp = "[0-9]+", message = "Invalid code format")
    @Size(min = 6,max = 6,message = "code must contain 6 characters")
    @NotBlank(message = "Code cannot be null or empty")
    private String code;
}