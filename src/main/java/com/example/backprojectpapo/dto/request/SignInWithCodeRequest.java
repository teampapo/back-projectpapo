package com.example.backprojectpapo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignInWithCodeRequest {
    @Size(max = 50,message = "\"email\" field should be no more than 50 characters long")
    @NotBlank(message = "Email cannot be null or empty")
    @Email(message = "Email should be valid")
    private String email;
    @Size(min = 6,max = 6,message = "code must contain 6 characters")
    @NotBlank(message = "Code cannot be null or empty")
    private String code;
}
