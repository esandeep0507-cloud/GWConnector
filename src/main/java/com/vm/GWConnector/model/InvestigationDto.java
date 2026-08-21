package com.example.claims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestigationDto {

    private String siuStatus;
    private String possibleFraudFlag;
    private String subrogationStatus;
}