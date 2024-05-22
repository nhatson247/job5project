package com.fpt.job5project.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobHomeDTO {
    long jobId;
    String jobName;
    long employerId;
    long minSalary;
    long maxSalary;
    String employerName;
    String location;
}
