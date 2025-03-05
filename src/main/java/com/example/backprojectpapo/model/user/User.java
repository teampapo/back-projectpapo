package com.example.backprojectpapo.model.user;

import com.example.backprojectpapo.model.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
@MappedSuperclass
public class User {
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}
