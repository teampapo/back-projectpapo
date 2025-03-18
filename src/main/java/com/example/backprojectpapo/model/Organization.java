package com.example.backprojectpapo.model;

import com.example.backprojectpapo.model.user.User;
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
public class Organization extends User {
    @Id
    @Column(name = "organization_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "organization_full_name",nullable = false)
    private String fullName;

    @Column(name ="organization_short_name",nullable = false)
    private String shortName;

    @Column(name = "inn",nullable = false)
    private String inn;

    @Column(name = "kpp",nullable = false)
    private String kpp;

    @Column(name = "ogrn",nullable = false)
    private String ogrn;

    @Column(name = "responsible_person_surname",nullable = false)
    private String responsiblePersonSurname;

    @Column(name = "responsible_person_name",nullable = false)
    private String responsiblePersonName;

    @Column(name = "responsible_person_patronymic")
    private String responsiblePersonPatronymic;

    @Column(name = "responsible_person_email",nullable = false)
    private String responsiblePersonEmail;

    @Column(name = "responsible_person_phone_number",nullable = false)
    private String responsiblePersonPhoneNumber;

    @Column(name = "add_info")
    private String addInfo;

    @OneToMany(mappedBy = "organization")
    private Set<ConnectionRequest> connectionRequests = new HashSet<>();

    @OneToMany(mappedBy = "organization")
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(mappedBy = "organization")
    private Set<ServiceRequest> serviceRequests = new HashSet<>();

    @OneToMany(mappedBy = "organization")
    private Set<ServiceDetail> serviceDetails = new HashSet<>();
}
