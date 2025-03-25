package com.example.backprojectpapo.dto.request;

import com.example.backprojectpapo.model.AggregatorSpecialist;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AggregatorSpecialistDTO {
    @Size(max = 50, message = "\"surname\" field should be no more than 50 characters long")
    private String surname;
    @Size(max = 50, message = "\"name\" field should be no more than 50 characters long")
    private String name;
    @Size(max = 50, message = "\"patronymic\" field should be no more than 50 characters long")
    private String patronymic;
    @Size(max = 30, message = "\"department\" field should be no more than 30 characters long")
    private String department;
    @Size(max = 20, message = "\"position\" field should be no more than 20 characters long")
    private String position;
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    @Size(max = 20, message = "\"phoneNumber\" field should be no more than 20 characters long")
    private String phoneNumber;
    @Size(max = 250, message = "\"addInfo\" field should be no more than 20 characters long")
    private String addInfo;


}
