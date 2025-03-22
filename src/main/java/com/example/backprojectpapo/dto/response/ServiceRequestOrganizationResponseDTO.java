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
public class ServiceRequestOrganizationResponseDTO {
    private Integer id;
    private CustomerOrganizationResponseDTO customerOrganizationResponseDTO;
    private LocalDateTime dateService;
    private String addInfo;
    private Set<ServiceDetailCustomerResponseDTO> serviceDetails;

    public static ServiceRequestOrganizationResponseDTO toDTO(ServiceRequest serviceRequest) {
        Set<ServiceDetailCustomerResponseDTO> serviceDetailDTOS = serviceRequest
                .getServiceDetails()
                .stream().map(ServiceDetailCustomerResponseDTO::toDto)
                .collect(Collectors.toSet());

        CustomerOrganizationResponseDTO customerOrganization = CustomerOrganizationResponseDTO.toDTO(serviceRequest.getCustomer()); ;

        return ServiceRequestOrganizationResponseDTO.builder()
                .id(serviceRequest.getId())
                .customerOrganizationResponseDTO(customerOrganization)
                .dateService(serviceRequest.getDateService())
                .addInfo(serviceRequest.getAddInfo())
                .serviceDetails(serviceDetailDTOS)
                .build();
    }
}
