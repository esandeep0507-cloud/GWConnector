package com.example.claims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LossLocationDto {

    private String address1;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}