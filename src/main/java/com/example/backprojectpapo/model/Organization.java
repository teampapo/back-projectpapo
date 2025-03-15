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
@Table(name = "organization")
@Schema(description = "Таблица - организация, оказывающая услуги транспортным средствам")
public class Organization extends User {
    @Id
    @Column(name = "organization_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор организации",
            example = "123",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Column(name = "organization_full_name",nullable = false)
    @Schema(description = "Полное наименование организации")
    private String fullName;

    @Column(name ="organization_short_name",nullable = false)
    @Schema(description = "Сокращённое наименование организации")
    private String shortName;

    @Column(name = "inn",nullable = false)
    @Schema(description = "ИНН")
    private String inn;

    @Column(name = "kpp",nullable = false)
    @Schema(description = "КПП")
    private String kpp;

    @Column(name = "ogrn",nullable = false)
    @Schema(description = "ОГРН")
    private String ogrn;

    @Column(name = "responsible_person_surname",nullable = false)
    @Schema(description = "Фамилия ответственного лица")
    private String responsiblePersonSurname;

    @Column(name = "responsible_person_name",nullable = false)
    @Schema(description = "Имя ответственного лица")
    private String responsiblePersonName;

    @Column(name = "responsible_person_patronymic")
    @Schema(description = "Отчество ответственного лица")
    private String responsiblePersonPatronymic;

    @Column(name = "responsible_person_email",nullable = false)
    @Schema(description = "Почта ответственного лица")
    private String responsiblePersonEmail;

    @Column(name = "responsible_person_phone_number",nullable = false)
    @Schema(description = "Телефон ответственного лица")
    private String responsiblePersonPhoneNumber;

    @Column(name = "add_info")
    @Schema(description = "Дополнительная информация")
    private String addInfo;

    @OneToMany(mappedBy = "organization")
    @Schema(description = "Запроса организации на подключение (прелставлено в id ) ")
    private Set<ConnectionRequest> connectionRequests = new HashSet<>();

    @OneToMany(mappedBy = "organization")
    @Schema(description = "Адресс (прелставлено в id ) ")
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(mappedBy = "organization")
    @Schema(description = "Заявка на предоставление услуги (прелставлено в id ) ")
    private Set<ServiceRequest> serviceRequests = new HashSet<>();
}
