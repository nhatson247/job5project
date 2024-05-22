package com.fpt.job5project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
