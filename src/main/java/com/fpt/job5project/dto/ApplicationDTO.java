package com.fpt.job5project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationDTO {
    private long applicationId;
    private long jobId;
    private long candidateId;
    private Date applicationDate;
    private long status;
    private String cv;

}
