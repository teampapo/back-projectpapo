package com.example.backprojectpapo.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TypeOfServiceRequestDTO {
    private Integer id;
    private String code;
    private String name;
}
