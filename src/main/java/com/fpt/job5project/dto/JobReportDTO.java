package com.fpt.job5project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobReportDTO {
    private long reportId;
    private long jobId;
    private long candidateId;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(timezone = "GMT+07:00")
    private Date reportDate;
    private String description;

}
