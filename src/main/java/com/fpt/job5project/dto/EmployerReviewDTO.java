package com.fpt.job5project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployerReviewDTO {
    private long reviewId;
    private long employerId;
    private long candidateId;
    private double score;
    @JsonFormat(timezone = "GMT+07:00")
    private Date reviewDate;

}
