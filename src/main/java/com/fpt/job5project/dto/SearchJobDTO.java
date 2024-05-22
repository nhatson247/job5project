package com.fpt.job5project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchJobDTO {
    private long industryId;
    private String searchValue;
    private long minSalary;
    private long maxSalary;
    private String location;
    private int experience;
    private int typeJob;
}
