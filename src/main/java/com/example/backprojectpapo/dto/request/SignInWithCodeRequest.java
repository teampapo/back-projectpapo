package com.example.backprojectpapo.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignInWithCodeRequest {
    private String email;
    private String code;
}
