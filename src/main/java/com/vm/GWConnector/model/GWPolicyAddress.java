package com.vm.GWConnector.model;

import com.vm.GWConnector.model.GWCodeName;
import lombok.Data;

@Data
public class GWPolicyAddress {

    private String addressLine1;
    private String city;
    private String country;
    private String county;
    private String displayName;
    private String id;
    private boolean policyAddress;
    private String policyLabel;
    private String postalCode;
    private GWCodeName state;
}