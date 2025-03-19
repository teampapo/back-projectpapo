package com.example.backprojectpapo.model;

import com.example.backprojectpapo.model.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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

    @ManyToOne
    @JoinColumn(name = "organiztion_id",nullable = false)
    private Organization organization;

    @Column(name = "reg_number",nullable = false)
    private String registrationNumber;

    @CreationTimestamp
    @Column(name = "date_begin",nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd.mm.yyyy")
    private LocalDate dateBegin;

    //@UpdateTimestamp
    @Column(name = "date_end")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd.mm.yyyy")
    private LocalDate dateEnd;

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "add_info")
    private String addInfo;

    @ManyToMany(mappedBy = "connectionRequests")
    private Set<AggregatorSpecialist> aggregatorSpecialists = new HashSet<>();

    @PreUpdate
    protected void onUpdate() {
        if (this.status == Status.COMPLETED || this.status == Status.REJECTED) {
            this.dateEnd = LocalDate.now();
        }
    }
}
