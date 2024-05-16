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
public class FollowDTO {
    private long followId;
    private long employerId;
    private long candidateId;
    @JsonFormat(timezone = "GMT+07:00")
    private Date followDate;

}
