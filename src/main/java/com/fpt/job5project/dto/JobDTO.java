package com.fpt.job5project.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobDTO {
    long jobId;
    String jobName;
    long employerId;
    String address;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(timezone = "GMT+07:00")
    Date postDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(timezone = "GMT+07:00")
    Date expirationDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(timezone = "GMT+07:00")
    Date acceptDate;
    int typeId;
    String jobPosition;
    int numPosition;
    long minSalary;
    long maxSalary;
    int yearExperience;
    String location;
    boolean isExpired;
    boolean isRemoved;
    int reupTimesLeft;
}
