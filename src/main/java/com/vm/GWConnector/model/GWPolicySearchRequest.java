package com.vm.GWConnector.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class GWPolicySearchRequest {
    private GWPolicySearchRequest.DataNode data;

    @lombok.Data
    public static class DataNode {
        private GWPolicySearchRequest.Attributes attributes;
    }

    @lombok.Data
    public static class Attributes {
        private String policyNumber;
    }

}