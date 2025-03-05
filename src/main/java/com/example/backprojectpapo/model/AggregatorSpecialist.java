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
@Table(name ="aggregator_specialist")
public class AggregatorSpecialist extends User {
    @Id
    @Column(name = "aggregator_specialists_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(name = "aggregator_specialist_surname")
    private String surname;

    @NonNull
    @Column(name = "aggregator_specialist_name")
    private String name;

    @Column(name = "aggregator_specialist_patronymic")
    private String patronymic;

    @NonNull
    @Column(name = "aggregator_specialist_department")
    private String department;

    @NonNull
    @Column(name = "aggregator_specialist_position")
    private String position;

    @NonNull
    @Column(name = "aggregator_specialist_phone_number")
    private String phoneNumber;

    @Column(name = "add_info")
    private String addInfo;

    @ManyToMany
    @JoinTable(
            name = "aggregator_specialist_connector_request",
            joinColumns = @JoinColumn(name = "aggregator_specialists_id"),
            inverseJoinColumns = @JoinColumn(name = "connection_request_id")
    )
    private Set<ConnectionRequest> connectionRequests = new HashSet<>();
}
