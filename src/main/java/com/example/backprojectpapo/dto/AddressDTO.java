package com.example.backprojectpapo.dto;

import com.example.backprojectpapo.model.Address;
import com.example.backprojectpapo.model.enums.AddressType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddressDTO {
    private String subjectName;
    private String cityName;
    private String streetName;
    private String houseNumber;
    private String addInfo;
    private AddressType addressType;

    public static AddressDTO toDto(Address address){
        return AddressDTO.builder()
                .subjectName(address.getSubjectName())
                .cityName(address.getCityName())
                .streetName(address.getStreetName())
                .houseNumber(address.getHouseNumber())
                .addInfo(address.getAddInfo())
                .addressType(address.getAddressType())
                .build();
    }
}
