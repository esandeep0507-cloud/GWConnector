package com.vm.GWConnector.model;

import lombok.Data;

import java.util.Map;

@Data
public class GWClaimSubmitData {

    private GWClaimAttributes attributes;
    private String checksum;
    private Map<String, GWLink> links;
}