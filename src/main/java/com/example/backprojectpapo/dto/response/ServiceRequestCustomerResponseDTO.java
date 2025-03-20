package com.example.backprojectpapo.dto.response;

import com.example.backprojectpapo.model.ServiceRequest;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ServiceRequestCustomerResponseDTO {
    private Integer id;
    private OrganizationCustomerResponseDTO organization;
    private LocalDateTime dateService;
    private String addInfo;
    private Set<ServiceDetailCustomerResponseDTO> serviceDetails;

    public static ServiceRequestCustomerResponseDTO toDto(ServiceRequest serviceRequest) {
        Set<ServiceDetailCustomerResponseDTO> serviceDetailDTOS = serviceRequest
                .getServiceDetails()
                .stream().map(ServiceDetailCustomerResponseDTO::toDto)
                .collect(Collectors.toSet());

        return ServiceRequestCustomerResponseDTO.builder()
                .id(serviceRequest.getId())
                .organization(OrganizationCustomerResponseDTO
                        .toDto(serviceRequest.getOrganization()))
                .dateService(serviceRequest.getDateService())
                .addInfo(serviceRequest.getAddInfo())
                .serviceDetails(serviceDetailDTOS)
                .build();
    }
}
