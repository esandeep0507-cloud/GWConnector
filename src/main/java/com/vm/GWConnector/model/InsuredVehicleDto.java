package com.example.claims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsuredVehicleDto {

    private String registrationNumber;
    private String vehicleType;
    private String make;
    private String model;
    private String year;
    private String vin;
    private String totalLossFlag;
    private String damageDescription;
    private String lossEstimate;
    private String pointOfFirstImpact;
    private String vehicleDrivable;
}