package com.example.claims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedestrianDto {

    private String name;
    private String injured;
    private String injurySeverity;
    private String injuryDescription;
}