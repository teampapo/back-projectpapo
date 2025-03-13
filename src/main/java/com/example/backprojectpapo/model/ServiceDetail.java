package com.example.backprojectpapo.model;

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
public class ServiceDetail {
    @Id
    @Column(name = "service_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "service_detail_code",nullable = false)
    private String code;

    @Column(name = "service_detail_name",nullable = false)
    private String name;

    @Column(name = "service_detail_cost",nullable = false)
    private Integer cost;

    @Column(name = "service_detail_duration",nullable = false)
    private Integer duration;

    @Column(name = "add_info")
    private String addInfo;

    @ManyToMany(mappedBy = "serviceDetails")
    private Set<ServiceRequest> serviceRequests = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "service_request_subservice",
            joinColumns = @JoinColumn(name = "service_request_id"),
            inverseJoinColumns = @JoinColumn(name = "subservice_id")
    )
    private Set<Subservice> subservices = new HashSet<>();
}
