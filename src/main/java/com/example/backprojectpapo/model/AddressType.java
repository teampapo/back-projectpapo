package com.example.backprojectpapo.model;

import com.example.backprojectpapo.model.enums.TypeName;
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
@Table(name = "address_type")
@Schema(description = "Таблица типа адреса")
public class AddressType {
    @Id
    @Column(name = "address_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор типа адреса",
            example = "123",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Column(name = "address_type_name",nullable = false)
    @Schema(description = "Наименование типа адреса")
    private TypeName typeName;

    @Column(name = "add_info")
    @Schema(description = "Дополнительная информация")
    private String addInfo;

    @OneToMany(mappedBy = "addressType",cascade = CascadeType.PERSIST)
    @Schema(description = "Связь с таблицей Address")
    private Set<Address> addresses = new HashSet<>();
}
