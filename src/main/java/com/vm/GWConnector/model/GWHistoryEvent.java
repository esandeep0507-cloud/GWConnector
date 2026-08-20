package com.vm.GWConnector.model;

import lombok.Data;

@Data
public class GWHistoryEvent {

    private String description;
    private String eventTimeStamp;
    private String type;
    private String user;
}