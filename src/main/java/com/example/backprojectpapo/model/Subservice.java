package com.example.backprojectpapo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity 
@Table(name = "subservice")
public class Subservice {
    @Id
    @Column(name = "subservice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cost",nullable = false)
    private String cost;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private TypeOfService type;

    @ManyToMany(mappedBy = "subservices")
    private Set<ServiceDetail> serviceDetails = new HashSet<>();
}
