package com.example.backprojectpapo.dto;

import com.example.backprojectpapo.model.Address;
import com.example.backprojectpapo.model.enums.AddressType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddressDTO {
    private Integer id;
    @NotBlank(message="The \"subjectName\" field must be filled in")
    @Size(max = 50,message = "\"subjectName\" field should be no more than 50 characters long")
    private String subjectName;
    @NotBlank(message="The \"cityName\" field must be filled in")
    @Size(max = 50,message = "\"cityName\" field should be no more than 50 characters long")
    private String cityName;
    @NotBlank(message="The \"streetName\" field must be filled in")
    @Size(max = 50,message = "\"streetName\" field should be no more than 50 characters long")
    private String streetName;
    @NotBlank(message="The \"houseNumber\" field must be filled in")
    @Size(max = 50,message = "\"houseNumber\" field should be no more than 10 characters long")
    private String houseNumber;
    @Size(max = 250, message = "\"addInfo\" field should be no more than 20 characters long")
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