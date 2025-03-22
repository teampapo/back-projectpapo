package com.example.backprojectpapo.model;

import com.example.backprojectpapo.model.enums.AddressType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Column(name = "address_type", nullable = false)
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
