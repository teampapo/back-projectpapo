package com.example.backprojectpapo.dto.response;

import com.example.backprojectpapo.model.ConnectionRequest;
import com.example.backprojectpapo.model.enums.Status;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ConnectionRequestResponseDTO {
    private Integer id;
    private OrganizationResponseDTO organization;
    private String registrationNumber;
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    private Status status;
    private String addInfo;

    public static ConnectionRequestResponseDTO toDto(ConnectionRequest connectionRequest){

        OrganizationResponseDTO organization = OrganizationResponseDTO.toDto(connectionRequest.getOrganization());

        return ConnectionRequestResponseDTO.builder()
                .id(connectionRequest.getId())
                .organization(organization)
                .registrationNumber(connectionRequest.getRegistrationNumber())
                .dateBegin(connectionRequest.getDateBegin())
                .dateEnd(connectionRequest.getDateEnd())
                .status(connectionRequest.getStatus())
                .addInfo(connectionRequest.getAddInfo())
                .build();
    };
}
