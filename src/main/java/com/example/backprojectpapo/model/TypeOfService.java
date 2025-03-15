package com.example.backprojectpapo.model;


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
@Table(name = "type_of_service")
@Schema(description = "Таблица - тип услуги")
public class TypeOfService {
    @Id
    @Column(name = "type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор типа услуги",
            example = "123",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Column(name = "type_code",nullable = false)
    @Schema(description = "Код типа услуги")
    private String code;

    @Column(name = "type_name",nullable = false)
    @Schema(description = "Наименование типа услуги")
    private String name;

    @OneToMany(mappedBy = "type", cascade = CascadeType.PERSIST)
    private Set<Subservice> subservices = new HashSet<>();
}
