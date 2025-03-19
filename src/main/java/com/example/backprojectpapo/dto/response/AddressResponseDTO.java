package com.example.backprojectpapo.dto.response;

import com.example.backprojectpapo.model.Address;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddressResponseDTO {
    private String subjectName;
    private String cityName;
    private String streetName;
    private String houseNumber;
    private String addInfo;

    public static AddressResponseDTO toDto(Address address){
        return AddressResponseDTO.builder()
                .subjectName(address.getSubjectName())
                .cityName(address.getCityName())
                .streetName(address.getStreetName())
                .houseNumber(address.getHouseNumber())
                .addInfo(address.getAddInfo())
                .build();
    }
}
