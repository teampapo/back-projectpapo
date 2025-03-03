package com.example.backprojectpapo.dto.search;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSearchCriteria extends BaseEntitySearchCriteria{
    private String surname;
    private String name;
    private String patronymic;
    private String phoneNumber;
    private String email;
}
