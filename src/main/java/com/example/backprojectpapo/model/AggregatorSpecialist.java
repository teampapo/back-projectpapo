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
@Table(name ="aggregator_specialist")
@Schema(description = "Таблица аггрегатор специалиста")
public class AggregatorSpecialist extends User {
    @Id
    @Column(name = "aggregator_specialists_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор типа аггрегатор специалиста",
            example = "123",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Column(name = "aggregator_specialist_surname",nullable = false)
    @Schema(description = "Фамилия")
    private String surname;

    @Column(name = "aggregator_specialist_name",nullable = false)
    @Schema(description = "Имя")
    private String name;

    @Column(name = "aggregator_specialist_patronymic")
    @Schema(description = "Отчество")
    private String patronymic;

    @Column(name = "aggregator_specialist_department",nullable = false)
    @Schema(description = "Наименование подразделения")
    private String department;

    @Column(name = "aggregator_specialist_position",nullable = false)
    @Schema(description = "Наименование должности")
    private String position;

    @Column(name = "aggregator_specialist_phone_number",nullable = false)
    @Schema(description = "Номер телефона")
    private String phoneNumber;

    @Column(name = "add_info")
    @Schema(description = "Дополнительная информация")
    private String addInfo;

    @ManyToMany
    @JoinTable(
            name = "aggregator_specialist_connector_request",
            joinColumns = @JoinColumn(name = "aggregator_specialists_id"),
            inverseJoinColumns = @JoinColumn(name = "connection_request_id")
    )
    @Schema(description = "Промежуточная таблица связи аггрегатора с запросом на добавление организации")
    private Set<ConnectionRequest> connectionRequests = new HashSet<>();
}
