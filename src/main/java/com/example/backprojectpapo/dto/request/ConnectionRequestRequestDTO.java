package com.example.backprojectpapo.dto.request;

import com.example.backprojectpapo.model.enums.Status;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ConnectionRequestRequestDTO {
    private Integer id;
    private Status status;
    private String addInfo;
}
