package com.example.backprojectpapo.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerPutDTO {
    private String email;
    private String surname;
    private String name;
    private String patronymic;
    private String phoneNumber;
    private String addInfo;
}
