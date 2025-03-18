package com.example.backprojectpapo.dto.response;

import com.example.backprojectpapo.model.Address;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddressCustomerResponseDTO {
    private String subjectName;
    private String cityName;
    private String streetName;
    private String houseNumber;
    private String addInfo;

    public static AddressCustomerResponseDTO toDto(Address address){
        return AddressCustomerResponseDTO.builder()
                .subjectName(address.getSubjectName())
                .cityName(address.getCityName())
                .streetName(address.getStreetName())
                .houseNumber(address.getHouseNumber())
                .addInfo(address.getAddInfo())
                .build();
    }
}
