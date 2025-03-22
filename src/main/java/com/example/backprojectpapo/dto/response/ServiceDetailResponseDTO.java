package com.example.backprojectpapo.dto.response;


import com.example.backprojectpapo.model.ServiceDetail;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ServiceDetailResponseDTO {
    private Integer id;
    private String code;
    private String name;
    private Integer cost;
    private Integer duration;
    private String addInfo;
    private OrganizationCustomerResponseDTO organizationCustomer;

    public static ServiceDetailResponseDTO toDTO(ServiceDetail serviceDetail) {
        OrganizationCustomerResponseDTO organizationCustomerResponseDTO = OrganizationCustomerResponseDTO.toDto(serviceDetail.getOrganization());

        return ServiceDetailResponseDTO.builder()
                .id(serviceDetail.getId())
                .code(serviceDetail.getCode())
                .name(serviceDetail.getName())
                .cost(serviceDetail.getCost())
                .duration(serviceDetail.getDuration())
                .addInfo(serviceDetail.getAddInfo())
                .organizationCustomer(organizationCustomerResponseDTO)
                .build();
    }
}
