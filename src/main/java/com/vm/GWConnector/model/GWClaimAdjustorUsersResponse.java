package com.vm.GWConnector.model;

import lombok.Data;

import java.util.List;

@Data
public class GWClaimAdjustorUsersResponse {

    private int count;
    private List<UserData> data;
    private ResponseLinks links;

    @Data
    public static class UserData {
        private UserAttributes attributes;
        private String checksum;
        private EntityLinks links;
    }

    @Data
    public static class UserAttributes {
        private String id;
        private int loadFactor;
        private Boolean manager;
        private Boolean member;
        private UserReference user;
    }

    @Data
    public static class UserReference {
        private String displayName;
        private String id;
        private String type;
        private String uri;
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
