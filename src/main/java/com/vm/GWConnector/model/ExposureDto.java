package com.example.claims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExposureDto {

    private String prospective;
    private String involved;
    private String openDate;
    private String closeDate;
    private String assignedName;
    private String adjusterGroup;
}