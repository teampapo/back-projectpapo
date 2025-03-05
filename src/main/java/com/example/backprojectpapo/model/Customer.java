package com.example.backprojectpapo.model;

import com.example.backprojectpapo.model.user.User;
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
@Table(name = "customer")
public class Customer extends User {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "customer_surname")
    private String surname;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "customer_patronymic")
    private String patronymic;

    @NonNull
    @Column(name = "customer_phone_number")
    private String phoneNumber;

    //@Column(name = "customer_email", nullable = false)
    //private String email;

    @Column(name = "add_info")
    private String addInfo;

    @OneToMany(mappedBy = "customer")
    private Set<ServiceRequest> serviceRequests = new HashSet<>();
}
