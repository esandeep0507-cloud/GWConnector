package com.vm.GWConnector.model;

import lombok.Data;

@Data
public class ClaimAdjustorResponse {

    private String id;
    private String type;
    private String name;
    private String description;
    private Boolean active;
    private String groupType;
    private String securityZone;
    private String selfLink;
}
