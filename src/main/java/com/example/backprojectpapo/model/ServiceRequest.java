package com.example.backprojectpapo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "service_request")
@Schema(description = "Таблица - Заявка на предоставление услуги")
public class ServiceRequest {
    @Id
    @Column(name ="service_request_id")
    @Schema(description = "Уникальный идентификатор заявки на предоставление услуги",
            example = "123",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    @Schema(description = "Клиент (прелставлено в id ) ")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "organization_id",nullable = false)
    @Schema(description = "Организация (прелставлено в id ) ")
    private Organization organization;

    @Column(name = "data_service",nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd.mm.yyyy hh:mm")
    @Schema(description = "Время и дата начала оказания услуги")
    private LocalDateTime dataService;

    @Column(name = "add_info")
    @Schema(description = "Дополнительная информация")
    private String addInfo;

    @ManyToMany
    @JoinTable(
            name = "service_request_detail",
            joinColumns = @JoinColumn(name = "service_request_id"),
            inverseJoinColumns = @JoinColumn(name = "service_detail_id")
    )
    @Schema(description = "Промежуточная таблица связи Заявка на предоставление услуги и Детализации услуги")
    private Set<ServiceDetail> serviceDetails = new HashSet<>();


}
