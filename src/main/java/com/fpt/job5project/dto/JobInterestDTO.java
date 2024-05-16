package com.fpt.job5project.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobInterestDTO {
    private long jobInterestId;
    private long jobId;
    private long candidateId;
    @JsonFormat(timezone = "GMT+07:00")
    private Date interestDate;

}
