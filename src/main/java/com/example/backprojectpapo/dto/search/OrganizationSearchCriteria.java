package com.example.backprojectpapo.dto.search;

import com.example.backprojectpapo.model.enums.Status;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationSearchCriteria extends BaseEntitySearchCriteria{
    private String fullName;
    private String shortName;
    private String inn;
    private String kpp;
    private String ogrn;
    private String responsiblePersonSurname;
    private String responsiblePersonName;
    private String responsiblePersonPatronymic;
    private String responsiblePersonEmail;
    private String responsiblePersonPhoneNumber;
    private Integer typeOfServiceId;
    private String typeOfServiceCode;
    private Status connectionRequestStatus;
}
