package com.example.claims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClaimLifecycleDto {

    private String closureDate;
    private String reopenDate;
    private String segmentedGroupName;
}