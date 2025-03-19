package com.example.backprojectpapo.dto.response;

import com.example.backprojectpapo.model.Customer;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomerResponseDTO {
    private String email;
    private String surname;
    private String name;
    private String patronymic;
    private String phoneNumber;
    private String addInfo;
    private String jwtToken;

    public static CustomerResponseDTO toDto(Customer customer){
        return CustomerResponseDTO.builder()
                .email(customer.getEmail())
                .surname(customer.getSurname())
                .name(customer.getName())
                .patronymic(customer.getPatronymic())
                .phoneNumber(customer.getPhoneNumber())
                .addInfo(customer.getAddInfo())
                .build();
    }
}
