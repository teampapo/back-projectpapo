package com.example.backprojectpapo.model.user;

import com.example.backprojectpapo.model.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
@MappedSuperclass
@Schema(description = "Базовый класс пользователя")
public class User {
    @Column(name = "email", nullable = false)
    @Schema(description = "Электронная почта пользователя", example = "user@example.com")
    private String email;

    @Column(name = "password")
    @Schema(description = "Пароль пользователя (хранится в зашифрованном виде)",
            example = "hashed_password", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @Schema(description = "Роль пользователя", example = "ADMIN",
            allowableValues = {"ADMIN", "CUSTOMER", "ORGANIZATION"})
    private Role role;

    @Column(name = "is_active", nullable = false)
    @Schema(description = "Активен ли пользователь", example = "true")
    private Boolean isActive;
}
