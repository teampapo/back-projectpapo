package com.example.backprojectpapo.dto.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ServiceDetailResponseDTO {
    private String code;
    private String name;
    private Integer cost;
    private Integer duration;
    private String addInfo;
}
