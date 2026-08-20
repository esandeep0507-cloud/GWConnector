package com.vm.GWConnector.model;

import lombok.Data;

import java.util.List;

@Data
public class GWLink {

    private String href;
    private List<String> methods;
}