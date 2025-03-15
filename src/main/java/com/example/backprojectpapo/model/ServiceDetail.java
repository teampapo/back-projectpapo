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
@Table(name = "service_detail")
@Schema(description = "Таблица - детализация услуги (работы в рамках услуги)")
public class ServiceDetail {
    @Id
    @Column(name = "service_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор детализации услуги",
            example = "123",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Column(name = "service_detail_code",nullable = false)
    @Schema(description = "Код услуги")
    private String code;

    @Column(name = "service_detail_name",nullable = false)
    @Schema(description = "Название услуги")
    private String name;

    @Column(name = "service_detail_cost",nullable = false)
    @Schema(description = "Стоимость услуги")
    private Integer cost;

    @Column(name = "service_detail_duration",nullable = false)
    @Schema(description = "Время выполнения услуги")
    private Integer duration;

    @Column(name = "add_info")
    @Schema(description = "Дополнительная информация")
    private String addInfo;

    @ManyToMany(mappedBy = "serviceDetails")
    @Schema(description = "Заявка на предоставление услуги (прелставлено в id ) ")
    private Set<ServiceRequest> serviceRequests = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "service_request_subservice",
            joinColumns = @JoinColumn(name = "service_request_id"),
            inverseJoinColumns = @JoinColumn(name = "subservice_id")
    )
    private Set<Subservice> subservices = new HashSet<>();
}
