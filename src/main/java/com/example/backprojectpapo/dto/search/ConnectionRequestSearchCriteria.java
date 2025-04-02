package com.example.backprojectpapo.dto.search;


import com.example.backprojectpapo.model.enums.Status;
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
    private Status status;
    private Integer aggregatorSpecialistId;

}
