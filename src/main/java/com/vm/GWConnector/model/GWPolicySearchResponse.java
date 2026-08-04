package com.vm.GWConnector.model;

import lombok.Data;

import java.util.List;

@Data
public class GWPolicySearchResponse {

    private int count;
    private List<PolicyData> data;
    private ResponseLinks links;

    @Data
    public static class PolicyData {
        private PolicyAttributes attributes;
        private EntityLinks links;
    }

    @Data
    public static class PolicyAttributes {
        private String accountNumber;
        private String effectiveDate;
        private String expirationDate;
        private String insuredName;
        private String policyAddress;
        private String policyId;
        private String policyNumber;
        private String producerOfRecordName;
        private String producerOfServiceName;
        private Product product;
    }

    @Data
    public static class Product {
        private String displayName;
        private String id;
    }

    @Data
    public static class EntityLinks {
        private Link self;
    }

    @Data
    public static class ResponseLinks {
        private Link first;
        private Link self;
    }

    @Data
    public static class Link {
        private String href;
        private List<String> methods;
    }
}