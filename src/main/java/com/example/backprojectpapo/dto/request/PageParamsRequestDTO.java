package com.example.backprojectpapo.dto.request;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class PageParamsRequestDTO {
    @PositiveOrZero()
    private Integer page = 0;

    @Positive()
    private Integer size = 10;
}
