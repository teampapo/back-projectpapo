package com.example.backprojectpapo.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ServiceDetailPutRequestDTO {
    private Integer id;
    private String code;
    private String name;
    private Integer cost;
    private Integer duration;
    private String addInfo;

}
