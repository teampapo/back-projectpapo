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

    @NonNull
    @Column(name = "organization_full_name")
    private String fullName;

    @NonNull
    @Column(name ="organization_short_name")
    private String shortName;

    @NonNull
    @Column(name = "inn")
    private String inn;

    @NonNull
    @Column(name = "kpp")
    private String kpp;

    @NonNull
    @Column(name = "ogrn")
    private String ogrn;

    @NonNull
    @Column(name = "responsible_person_surname")
    private String responsiblePersonSurname;

    @NonNull
    @Column(name = "responsible_person_name")
    private String responsiblePersonName;

    @Column(name = "responsible_person_patronymic")
    private String responsiblePersonPatronymic;

    @NonNull
    @Column(name = "responsible_person_email")
    private String responsiblePersonEmail;

    @NonNull
    @Column(name = "responsible_person_phone_number")
    private String responsiblePersonPhoneNumber;

    @Column(name = "add_info")
    private String addInfo;

    @OneToMany(mappedBy = "organization")
    private Set<ConnectionRequest> connectionRequests = new HashSet<>();

    @OneToMany(mappedBy = "organization")
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(mappedBy = "organization")
    private Set<ServiceRequest> serviceRequests = new HashSet<>();
}
