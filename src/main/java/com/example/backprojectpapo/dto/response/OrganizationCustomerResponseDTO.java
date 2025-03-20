package com.example.backprojectpapo.dto.response;

import com.example.backprojectpapo.dto.AddressDTO;
import com.example.backprojectpapo.model.Organization;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrganizationCustomerResponseDTO {
    private Integer id;
    private String fullName;
    private String shortName;
    private Set<AddressDTO> addresses;
    private String addInfo;

    public static OrganizationCustomerResponseDTO toDto(Organization organization){
        Set<AddressDTO> addressDTOs = organization.getAddresses()
                .stream().map(AddressDTO::toDto)
                .collect(Collectors.toSet());

        return OrganizationCustomerResponseDTO.builder()
                .id(organization.getId())
                .fullName(organization.getFullName())
                .shortName(organization.getShortName())
                .addresses(addressDTOs)
                .addInfo(organization.getAddInfo())
                .build();
    }
}
