package com.example.backprojectpapo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestDto {
    @JsonProperty("aboba")
    private String aboba;
    @JsonProperty("srulic")
    private String srulic;
    @JsonProperty("lashka")
    private String lashka;
}
