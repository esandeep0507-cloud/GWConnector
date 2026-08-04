package com.vm.GWConnector.model;

import lombok.Data;

import java.util.List;

@Data
public class GWClaimAdjustorResponse {

    private List<GroupData> data;
    private ResponseLinks links;
    private Meta meta;

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
    }

    @Data
    public static class EntityLinks {
        private Link self;
    }

    @Data
    public static class ResponseLinks {
        private Link self;
    }

    @Data
    public static class Meta {
        private PageInfo pageInfo;
    }

    @Data
    public static class PageInfo {
        private int totalCount;
        private int pageSize;
        private int pageNumber;
    }

    @Data
    public static class Link {
        private String href;
        private List<String> methods;
    }
}
