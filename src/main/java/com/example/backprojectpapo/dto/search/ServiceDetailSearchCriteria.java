package com.example.backprojectpapo.dto.search;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDetailSearchCriteria extends BaseEntitySearchCriteria{
    private Integer typeId;
    private String code;
    private String name;
    private String cost;
}
