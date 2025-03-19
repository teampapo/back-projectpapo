package com.example.backprojectpapo.dto.request;

import com.example.backprojectpapo.dto.response.AddressResponseDTO;
import com.example.backprojectpapo.dto.response.OrganizationResponseDTO;
import com.example.backprojectpapo.model.Organization;
import com.example.backprojectpapo.model.enums.Status;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrganizationPostRequestDTO {
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
    private String addInfo;
    private String email;
    private Set<AddressResponseDTO> addresses;

}
