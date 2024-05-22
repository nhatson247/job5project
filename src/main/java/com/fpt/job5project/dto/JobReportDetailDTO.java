
package com.fpt.job5project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobReportDetailDTO {
    private long reportId;
    private long jobId;
    private long candidateId;
    private String fullname;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(timezone = "GMT+07:00")
    private Date reportDate;
    private String description;
    private String employerId;
    private String employername;
}
