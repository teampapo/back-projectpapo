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
@Table(name = "address_type")
public class AddressType {
    @Id
    @Column(name = "address_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(name = "address_type_name")
    private String typeName;

    @Column(name = "add_info")
    private String addInfo;

    @OneToMany(mappedBy = "addressType",cascade = CascadeType.PERSIST)
    private Set<Address> addresses = new HashSet<>();
}
