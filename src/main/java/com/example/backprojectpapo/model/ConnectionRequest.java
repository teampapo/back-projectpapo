package com.example.backprojectpapo.model;

import com.example.backprojectpapo.model.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "connection_request")
@Schema(description = "Таблица запроса организации на подключение к сайту")
public class ConnectionRequest {
    @Id
    @Column(name = "connection_request_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор типа аггрегатор специалиста",
            example = "123",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "organiztion_id",nullable = false)
    @Schema(description = "Организация (прелставлено в id ) ")
    private Organization organization;

    @Column(name = "reg_number",nullable = false)
    @Schema(description = "Регистрационный номер заявки ")
    private String registrationNumber;

    @Column(name = "date_begin",nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd.mm.yyyy")
    @Schema(description = "Дата регистрации ")
    private LocalDate dateBegin;

    @Column(name = "date_end")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd.mm.yyyy")
    @Schema(description = "Дата окончания рассмотрения заявки")
    private LocalDate dateEnd;

    @Column(name = "status",nullable = false)
    @Schema(description = "Статус заявки")
    private Status status;

    @Column(name = "add_info")
    @Schema(description = "Дополнительная информация")
    private String addInfo;

    @ManyToMany(mappedBy = "connectionRequests")
    @Schema(description = "Специалист, отвечабщий за заявку")
    private Set<AggregatorSpecialist> aggregatorSpecialists = new HashSet<>();
}
