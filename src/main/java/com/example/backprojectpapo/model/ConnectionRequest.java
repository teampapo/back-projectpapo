package com.example.backprojectpapo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "connection_request")
public class ConnectionRequest {
    @Id
    @Column(name = "connection_request_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "organiztion_id")
    private Organization organization;

    @NonNull
    @Column(name = "reg_number")
    private String registrationNumber;

    @NonNull
    @Column(name = "date_begin")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd.mm.yyyy")
    private LocalDate dateBegin;

    @Column(name = "date_end")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd.mm.yyyy")
    private LocalDate dateEnd;

    @NonNull
    @Column(name = "status")
    private String status;

    @Column(name = "add_info")
    private String addInfo;

    @ManyToMany(mappedBy = "connectionRequests")
    private Set<AggregatorSpecialist> aggregatorSpecialists = new HashSet<>();
}
