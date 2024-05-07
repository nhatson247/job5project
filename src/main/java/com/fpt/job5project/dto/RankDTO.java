package com.fpt.job5project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RankDTO {
    private long rankId;
    private String rankName;
    private String description;
    private double price;
    private int displayTime;
    private int reupTimes;
    private int numApplications;
    private int limitPost;
    private String photo;


}
