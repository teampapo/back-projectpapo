package com.example.backprojectpapo.dto.response;

import com.example.backprojectpapo.dto.AddressDTO;
import com.example.backprojectpapo.model.ConnectionRequest;
import com.example.backprojectpapo.model.Organization;
import com.example.backprojectpapo.model.enums.Status;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrganizationGetAggregatorDTO {
    private Integer organizationId;
    private String organizationFullName;
    private String organizationShortName;
    private String inn;
    private String kpp;
    private String ogrn;
    private String responsiblePersonSurname;
    private String responsiblePersonName;
    private String responsiblePersonPatronymic;
    private String responsiblePersonEmail;
    private String responsiblePersonPhone;
    private Status requestStatus;
    private Set<AddressDTO> addresses;

    public static OrganizationGetAggregatorDTO toDTO(Organization organization) {

        List<ConnectionRequest> connectionRequest = organization.getConnectionRequests().stream().toList();
        Status status = connectionRequest.get(connectionRequest.size()-1).getStatus();

        Set<AddressDTO> addressDTOs = organization.getAddresses()
                .stream().map(AddressDTO::toDto)
                .collect(Collectors.toSet());

        return OrganizationGetAggregatorDTO.builder()
                .organizationId(organization.getId())
                .organizationFullName(organization.getFullName())
                .organizationShortName(organization.getShortName())
                .inn(organization.getInn())
                .kpp(organization.getKpp())
                .ogrn(organization.getOgrn())
                .responsiblePersonSurname(organization.getResponsiblePersonSurname())
                .responsiblePersonName(organization.getResponsiblePersonName())
                .responsiblePersonPatronymic(organization.getResponsiblePersonPatronymic())
                .responsiblePersonEmail(organization.getResponsiblePersonEmail())
                .responsiblePersonPhone(organization.getResponsiblePersonPhoneNumber())
                .requestStatus(status)
                .addresses(addressDTOs)
                .build();
    }

}
