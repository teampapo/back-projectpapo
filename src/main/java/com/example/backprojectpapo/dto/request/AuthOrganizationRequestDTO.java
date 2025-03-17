package com.example.backprojectpapo.dto.request;


import com.example.backprojectpapo.dto.UserDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AuthOrganizationRequestDTO extends UserDto {
    private String fullName;
    private String shortName;
    private String inn;
    private String kpp;
    private String ogrn;
    private String responsiblePersonSurname;
    private String responsiblePersonName;
    private String responsiblePersonPatronymic;
    private String responsiblePersonEmail;
    private String responsiblePersonPhoneNumber;
    private String addInfo;
}
