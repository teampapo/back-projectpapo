package com.example.backprojectpapo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "service_request")
public class ServiceRequest {
    @Id
    @Column(name ="service_request_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "organization_id",nullable = false)
    private Organization organization;

    @Column(name = "date_service",nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd.mm.yyyy hh:mm")
    private LocalDateTime dateService;

    @Column(name = "add_info")
    private String addInfo;

    @ManyToMany
    @JoinTable(
            name = "service_request_detail",
            joinColumns = @JoinColumn(name = "service_request_id"),
            inverseJoinColumns = @JoinColumn(name = "service_detail_id")
    )
    private Set<ServiceDetail> serviceDetails = new HashSet<>();


}
