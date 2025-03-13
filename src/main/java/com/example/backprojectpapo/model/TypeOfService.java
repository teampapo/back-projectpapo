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
@Table(name = "type_of_service")
public class TypeOfService {
    @Id
    @Column(name = "type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type_code",nullable = false)
    private String code;

    @Column(name = "type_name",nullable = false)
    private String name;

    @OneToMany(mappedBy = "type", cascade = CascadeType.PERSIST)
    private Set<Subservice> subservices = new HashSet<>();
}
