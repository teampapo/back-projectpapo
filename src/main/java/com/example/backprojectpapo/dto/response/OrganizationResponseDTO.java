package com.example.backprojectpapo.dto.response;

import com.example.backprojectpapo.dto.AddressDTO;
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
public class OrganizationResponseDTO {
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
    private Set<AddressDTO> addresses;
    private Status connectionRequestStatus;
    private String jwtToken;


    public static OrganizationResponseDTO toDto(Organization organization) {
        Set<AddressDTO> addressDTOs = organization.getAddresses()
                .stream().map(AddressDTO::toDto)
                .collect(Collectors.toSet());

        return OrganizationResponseDTO.builder()
                .fullName(organization.getFullName())
                .shortName(organization.getShortName())
                .inn(organization.getInn())
                .kpp(organization.getKpp())
                .ogrn(organization.getOgrn())
                .responsiblePersonSurname(organization.getResponsiblePersonSurname())
                .responsiblePersonName(organization.getResponsiblePersonName())
                .responsiblePersonPatronymic(organization.getResponsiblePersonPatronymic())
                .responsiblePersonEmail(organization.getResponsiblePersonEmail())
                .responsiblePersonPhoneNumber(organization.getResponsiblePersonPhoneNumber())
                .addInfo(organization.getAddInfo())
                .email(organization.getEmail())
                .addresses(addressDTOs)
                .build();

    }
}
