package com.example.backprojectpapo.dto.request;

import com.example.backprojectpapo.model.TypeOfService;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ServiceDetailPostRequestDTO {
    private Integer typeOfService;
    private String code;
    private String name;
    private Integer cost;
    private Integer duration;
    private String addInfo;
}
