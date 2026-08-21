package com.example.claims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDto {

    private String name;
    private String employeeId;
    private String licenseNumber;
    private String licenseState;
    private String licenseCountry;
    private String licenseClass;
    private String licenseExpiryDate;
    private String phoneNumber;
    private String email;
    private String injured;
    private String injurySeverity;
    private String injuryDescription;
    private String seatbeltUsed;
    private String airbagDeployed;
    private String alcoholOrDrugTestPerformed;
    private String alcoholOrDrugTestResult;
}