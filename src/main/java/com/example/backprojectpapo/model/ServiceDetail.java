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

    @NonNull
    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeOfService type;

    @NonNull
    @Column(name = "service_detail_code")
    private String code;

    @NonNull
    @Column(name = "service_detail_name")
    private String name;

    @NonNull
    @Column(name = "service_detail_cost")
    private Integer cost;

    @NonNull
    @Column(name = "service_detail_duration")
    private Integer duration;

    @Column(name = "add_info")
    private String addInfo;

    @ManyToMany(mappedBy = "serviceDetails")
    private Set<ServiceRequest> serviceRequests = new HashSet<>();
}
