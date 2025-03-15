package com.example.backprojectpapo.model;

import com.example.backprojectpapo.model.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer")
@Schema(description = "Таблица - клиент-владелец транспортного средства, обращающийся за предоставлением услуг")
public class Customer extends User {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор клиента",
            example = "123",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Column(name = "customer_surname")
    @Schema(description = "Фамилия")
    private String surname;

    @Column(name = "customer_name")
    @Schema(description = "Имя")
    private String name;

    @Column(name = "customer_patronymic")
    @Schema(description = "Отчество")
    private String patronymic;

    @Column(name = "customer_phone_number",nullable = false)
    @Schema(description = "Номер телефона")
    private String phoneNumber;

    @Column(name = "add_info")
    @Schema(description = "Дополнительная информация")
    private String addInfo;

    @OneToMany(mappedBy = "customer")
    @Schema(description = "Связь с таблицей ServiceRequests")
    private Set<ServiceRequest> serviceRequests = new HashSet<>();
}
