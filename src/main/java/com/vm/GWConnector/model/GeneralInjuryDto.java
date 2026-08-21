package com.example.claims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralInjuryDto {

    private String severity;
    private String bodyAreasInjured;
    private String bodyPart;
    private String icdCodes;
    private String description;
}