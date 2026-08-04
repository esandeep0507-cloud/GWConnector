package com.vm.GWConnector.model;

import lombok.Data;

@Data
public class PolicyResponse {

    private String policyNumber;
    private String policyId;
    private String accountNumber;
    private String insuredName;
    private String effectiveDate;
    private String expirationDate;
    private String policyAddress;
    private String productId;
    private String productName;
}