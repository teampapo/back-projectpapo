package com.example.backprojectpapo.model.jwt;

import com.example.backprojectpapo.model.enums.Role;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtData {
    private Integer id;
    private String email;
    private Role role;
    private LocalDateTime createdDateTime;
}
