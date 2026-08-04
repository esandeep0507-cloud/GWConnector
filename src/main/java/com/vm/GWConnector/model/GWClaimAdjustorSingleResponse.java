package com.vm.GWConnector.model;

import lombok.Data;

import java.util.List;

@Data
public class GWClaimAdjustorSingleResponse {

    private GroupData data;

    @Data
    public static class GroupData {
        private String id;
        private String type;
        private GroupAttributes attributes;
        private EntityLinks links;
    }

    @Data
    public static class GroupAttributes {
        private String id;
        private String name;
        private String displayName;
        private String description;
        private Boolean active;
        private Object groupType;
        private Object securityZone;
        private Object parent;
        private Object supervisor;
    }

    @Data
    public static class EntityLinks {
        private Link self;
        private Link users;
        private Link queues;
        private Link regions;
    }

    @Data
    public static class Link {
        private String href;
        private List<String> methods;
    }
}
