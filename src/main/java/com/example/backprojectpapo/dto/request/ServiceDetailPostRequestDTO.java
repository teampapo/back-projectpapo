package com.example.backprojectpapo.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ServiceDetailPostRequestDTO {
    private Integer organizationId;
    private Integer typeId;

}
