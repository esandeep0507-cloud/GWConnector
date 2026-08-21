package com.example.claims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoliceDto {

    private String policeAttended;
    private String policeReportAvailable;
    private String policeReportNumber;
    private String policeStation;
    private String officerName;
}