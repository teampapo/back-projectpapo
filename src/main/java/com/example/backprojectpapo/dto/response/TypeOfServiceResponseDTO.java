package com.example.backprojectpapo.dto.response;

import com.example.backprojectpapo.model.TypeOfService;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TypeOfServiceResponseDTO {
    private Integer id;
    private String code;
    private String name;

    public static TypeOfServiceResponseDTO toDTO(TypeOfService typeOfService) {
        return TypeOfServiceResponseDTO.builder()
                .id(typeOfService.getId())
                .code(typeOfService.getCode())
                .name(typeOfService.getName())
                .build();
    }
}
