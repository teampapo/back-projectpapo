package com.example.backprojectpapo.dto.search;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequestSearchCriteria extends BaseEntitySearchCriteria{
    private Integer customerId;
    private Integer organizationId;
    private LocalDateTime startDate;
    private LocalDateTime fromDateService;
    private LocalDateTime toDateService;
}
