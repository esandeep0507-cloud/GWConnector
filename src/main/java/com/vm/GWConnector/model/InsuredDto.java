package com.example.claims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsuredDto {

    private String insuredId;
    private String name;
    private String policyStatus;
    private String contactNumber;
    private String email;
}