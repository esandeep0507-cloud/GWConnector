package com.example.claims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    private String role;
    private String netTotalIncurredAmount;
    private String recoveryCategory;
    private String recoveryAmount;
}