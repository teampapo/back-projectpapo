package com.example.backprojectpapo.dto.response;

import com.example.backprojectpapo.model.Customer;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomerOrganizationResponseDTO {
    private Integer id;
    private String surname;
    private String name;
    private String patronymic;
    private String phoneNumber;

    public static CustomerOrganizationResponseDTO toDTO(Customer customer) {
        return CustomerOrganizationResponseDTO.builder()
                .id(customer.getId())
                .surname(customer.getSurname())
                .name(customer.getName())
                .patronymic(customer.getPatronymic())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }

}
