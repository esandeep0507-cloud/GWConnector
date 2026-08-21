package com.example.claims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LitigationDto {

    private String involved;
    private String matterType;
    private String courtType;
    private String courtDistrict;
    private String legalSpecialty;
    private String primaryCause;
    private String plaintiffAttorney;
    private String plaintiffLawFirm;
    private String defenseAttorney;
    private String defenseLawFirm;
}