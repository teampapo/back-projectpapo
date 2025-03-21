package com.example.backprojectpapo.dto.request;

import com.example.backprojectpapo.model.AggregatorSpecialist;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AggregatorSpecialistDTO {
    private String surname;
    private String name;
    private String patronymic;
    private String department;
    private String position;
    private String phoneNumber;
    private String addInfo;


}
