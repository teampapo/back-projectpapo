package com.example.backprojectpapo.dto.response;

import com.example.backprojectpapo.model.Organization;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrganizationCustomerResponseDTO {
    private String fullName;
    private String shortName;
    private Set<AddressCustomerResponseDTO> addresses;
    private String addInfo;

    public static OrganizationCustomerResponseDTO toDto(Organization organization){
        Set<AddressCustomerResponseDTO> addressDTOs = organization.getAddresses()
                .stream().map(AddressCustomerResponseDTO::toDto)
                .collect(Collectors.toSet());

        return OrganizationCustomerResponseDTO.builder()
                .fullName(organization.getFullName())
                .shortName(organization.getShortName())
                .addresses(addressDTOs)
                .addInfo(organization.getAddInfo())
                .build();
    }
}
