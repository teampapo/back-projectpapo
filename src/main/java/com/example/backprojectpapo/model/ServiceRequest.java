package com.example.backprojectpapo.model;

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
public class ServiceRequest {
    @Id
    @Column(name ="service_request_id")
    private Integer id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @NonNull
    @Column(name = "data_service")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd.mm.yyyy hh:mm")
    private LocalDateTime dataService;

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
