package com.example.backprojectpapo.dto.search;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntitySearchCriteria {
    private Integer id;
    @PositiveOrZero()
    private Integer page = 0;

    @Positive()
    private Integer size = 10;
}
