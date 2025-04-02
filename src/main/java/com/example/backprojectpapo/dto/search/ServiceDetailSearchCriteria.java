package com.example.backprojectpapo.dto.search;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDetailSearchCriteria extends BaseEntitySearchCriteria{
    private Integer organizationId;
    private Integer typeId; // id TypeOfServices
    private String code;
    private String name;
    private Integer cost;
    private Integer duration;
    private String typeName; // name TypeOfServices
    private String typeCode; // code TypeOfServices
}
