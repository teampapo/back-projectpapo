package com.example.backprojectpapo.model;

import com.example.backprojectpapo.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Builder
@Entity
@Table(name ="aggregator_specialist")
@AllArgsConstructor
public class AggregatorSpecialist extends User {
    @Id
    @Column(name = "aggregator_specialists_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "aggregator_specialist_surname",nullable = false)
    private String surname;

    @Column(name = "aggregator_specialist_name",nullable = false)
    private String name;

    @Column(name = "aggregator_specialist_patronymic")
    private String patronymic;

    @Column(name = "aggregator_specialist_department",nullable = false)
    private String department;

    @Column(name = "aggregator_specialist_position",nullable = false)
    private String position;

    @Column(name = "aggregator_specialist_phone_number",nullable = false)
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
