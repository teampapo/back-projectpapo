package com.example.backprojectpapo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "organization_id",nullable = false)
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "address_type_id",nullable = false)
    private AddressType addressType;

    @Column(name = "subject_name",nullable = false)
    private String subjectName;

    @Column(name = "city_name",nullable = false)
    private String cityName;

    @Column(name = "street_name",nullable = false)
    private String streetName;

    @Column(name = "house_number",nullable = false)
    private String houseNumber;

    @Column(name = "add_info")
    private String addInfo;
}
