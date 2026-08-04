package com.vm.GWConnector.model;

import lombok.Data;

@Data
public class GWLossLocation {

    private String addressLine1;
    private String city;
    private String country;
    private String displayName;
    private String id;
    private Boolean policyAddress;
    private String postalCode;
    private GWState state;
}