package com.example.backprojectpapo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Schema(description = "Таблица адрес организации, оказывающей услуги транспортным средствам")

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор адреса",
            example = "123",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "organization_id",nullable = false)
    @Schema(description = "организация (прелставлено в id ) ")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "address_type_id",nullable = false)
    @Schema(description = "тип адреса")
    private AddressType addressType;

    @Column(name = "subject_name",nullable = false)
    @Schema(description = "Наименование субъекта Российской Федерации")
    private String subjectName;

    @Column(name = "city_name",nullable = false)
    @Schema(description = "Наименование города")
    private String cityName;

    @Column(name = "street_name",nullable = false)
    @Schema(description = "Наименование улицы")
    private String streetName;

    @Column(name = "house_number",nullable = false)
    @Schema(description = "Номер дома")
    private String houseNumber;

    @Column(name = "add_info")
    @Schema(description = "Дополнительная информация")
    private String addInfo;
}
