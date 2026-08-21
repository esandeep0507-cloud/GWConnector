package com.example.claims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThirdPartyDto {

    private String involved;
    private String name;
    private String phone;
    private String email;
    private String insurer;
    private String policyNumber;
    private String injurySeverity;
    private String injuryDescription;

    private ThirdPartyVehicleDto vehicle;
}