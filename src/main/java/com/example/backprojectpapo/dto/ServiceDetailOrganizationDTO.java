package com.example.backprojectpapo.dto;

import com.example.backprojectpapo.model.ServiceDetail;
import com.example.backprojectpapo.model.TypeOfService;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ServiceDetailOrganizationDTO {
    private Integer id;
    private String code;
    private String name;
    private Integer cost;
    private Integer duration;
    private String addInfo;
    private Integer typeId;
    private String typeName;

    public static ServiceDetailOrganizationDTO toDto (ServiceDetail serviceDetail) {
        return ServiceDetailOrganizationDTO.builder()
                .id(serviceDetail.getId())
                .code(serviceDetail.getCode())
                .name(serviceDetail.getName())
                .cost(serviceDetail.getCost())
                .duration(serviceDetail.getDuration())
                .addInfo(serviceDetail.getAddInfo())
                .typeId(serviceDetail.getType().getId())
                .typeName(serviceDetail.getType().getName())
                .build();
    }
}
