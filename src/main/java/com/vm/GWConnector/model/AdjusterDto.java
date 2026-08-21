package com.example.claims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdjusterDto {

    private String nameAndId;
    private String dueDate;
    private String owner;
    private String groupName;
    private String region;
    private String experienceLevel;
}