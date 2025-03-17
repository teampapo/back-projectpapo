package com.example.backprojectpapo.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignInWithCodeAndPasswordRequest {
    private String email;
    private String password;
    private String code;
}
