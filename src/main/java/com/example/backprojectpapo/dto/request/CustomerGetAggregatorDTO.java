package com.example.backprojectpapo.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerGetAggregatorDTO {
    private Integer customerId;
    private String customerSurname;
    private String customerName;
    private String customerPatronymic;
    private String customerEmail;
    private String customerPhoneNumber;
}
