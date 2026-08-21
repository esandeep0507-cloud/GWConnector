package com.example.claims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DamageDto {

    private String firstPartyDamageDescription;
    private String thirdPartyDamageDescription;
    private String propertyDamageDescription;
    private String repairEstimate;
    private String totalLossIndicator;
}