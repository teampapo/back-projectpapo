package com.example.backprojectpapo.dto.request;


import com.example.backprojectpapo.dto.AddressDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrganizationPostRequestDTO {
    @Size(max = 150,message = "\"fullName\" field should be no more than 150 characters long")
    @Size(max = 50,message = "\"shortName\" field should be no more than 50 characters long")
    private String shortName;
    @Size(max = 10,message = "\"inn\" field should be no more than 10 characters long")
    private String inn;
    @Size(max = 9,message = "\"kpp\" field should be no more than 9 characters long")
    private String kpp;
    @Size(max = 13,message = "\"ogrn\" field should be no more than 13 characters long")
    private String ogrn;
    @Size(max = 50,message = "\"responsiblePersonSurname\" field should be no more than 50 characters long")
    private String responsiblePersonSurname;
    @Size(max = 50,message = "\"responsiblePersonName\" field should be no more than 50 characters long")
    private String responsiblePersonName;
    @Size(max = 50,message = "\"responsiblePersonPatronymic\" field should be no more than 50 characters long")
    private String responsiblePersonPatronymic;
    @Size(max = 50,message = "\"responsiblePersonEmail\" field should be no more than 50 characters long")
    private String responsiblePersonEmail;
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    @Size(max = 20,message = "\"responsiblePersonPhoneNumber\" field should be no more than 20 characters long")
    private String responsiblePersonPhoneNumber;
    @Size(max = 250, message = "\"addInfo\" field should be no more than 20 characters long")
    private String addInfo;
    @Size(max = 50,message = "\"email\" field should be no more than 50 characters long")
    @Email(message = "Email should be valid")
    private String email;
    private Set<AddressDTO> addresses;

}
