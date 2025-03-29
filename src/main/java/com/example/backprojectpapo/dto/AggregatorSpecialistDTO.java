package com.example.backprojectpapo.dto;

import com.example.backprojectpapo.model.AggregatorSpecialist;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AggregatorSpecialistDTO {
    private String email;
    //private String password;
    private String surname;
    private String name;
    private String patronymic;
    private String department;
    private String position;
    private String phoneNumber;
    private String addInfo;
    private String jwtToken;

    public static AggregatorSpecialistDTO toDTO(AggregatorSpecialist aggregatorSpecialist) {
        return AggregatorSpecialistDTO.builder()
                .email(aggregatorSpecialist.getEmail())
                //.password(aggregatorSpecialist.getPassword())
                .surname(aggregatorSpecialist.getSurname())
                .name(aggregatorSpecialist.getName())
                .patronymic(aggregatorSpecialist.getPatronymic())
                .department(aggregatorSpecialist.getDepartment())
                .position(aggregatorSpecialist.getPosition())
                .phoneNumber(aggregatorSpecialist.getPhoneNumber())
                .addInfo(aggregatorSpecialist.getAddInfo())
                .build();
    }
}
