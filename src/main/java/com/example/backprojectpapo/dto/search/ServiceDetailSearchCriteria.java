package com.example.backprojectpapo.dto.search;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDetailSearchCriteria extends BaseEntitySearchCriteria{
    private Integer organizationId;
    private Integer typeId;
    private String code;
    private String name;
    private Integer cost;
    private Integer duration;
    private String typeName;
    private String typeCode;
}
