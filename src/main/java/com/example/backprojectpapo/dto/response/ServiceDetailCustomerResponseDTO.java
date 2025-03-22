package com.example.backprojectpapo.dto.response;

import com.example.backprojectpapo.model.ServiceDetail;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ServiceDetailCustomerResponseDTO {
    private Integer id;
    private String code;
    private String name;
    private Integer cost;
    private Integer duration;
    private String addInfo;

    public static ServiceDetailCustomerResponseDTO toDto(ServiceDetail serviceDetail){
        return ServiceDetailCustomerResponseDTO.builder()
                .id(serviceDetail.getId())
                .code(serviceDetail.getCode())
                .name(serviceDetail.getName())
                .cost(serviceDetail.getCost())
                .duration(serviceDetail.getDuration())
                .addInfo(serviceDetail.getAddInfo())
                .build();
    }
}
