package com.example.backprojectpapo.dto.search;


import lombok.*;


import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionRequestSearchCriteria extends BaseEntitySearchCriteria{
    private Integer organizationId;
    private String registrationNumber;
    private LocalDate fromDateBegin;
    private LocalDate toDateBegin;
    private LocalDate fromDateEnd;
    private LocalDate toDateEnd;
    private String status;
    private Integer aggregatorSpecialistId;
}
