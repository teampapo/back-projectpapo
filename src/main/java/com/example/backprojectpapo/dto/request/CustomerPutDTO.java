package com.example.backprojectpapo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerPutDTO {
    @Size(max = 50,message = "\"email\" field should be no more than 50 characters long")
    @Email(message = "Email should be valid")
    private String email;
    @Size(max = 50,message = "\"surname\" field should be no more than 50 characters long")
    private String surname;
    @Size(max = 50,message = "\"name\" field should be no more than 50 characters long")
    private String name;
    @Size(max = 50,message = "\"patronymic\" field should be no more than 50 characters long")
    private String patronymic;
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    @Size(max = 20,message = "\"phoneNumber\" field should be no more than 20 characters long")
    private String phoneNumber;
    @Size(max = 250, message = "\"addInfo\" field should be no more than 20 characters long")
    private String addInfo;
}
