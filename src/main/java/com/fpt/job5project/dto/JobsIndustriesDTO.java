package com.fpt.job5project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Entity
//@IdClass(JobsIndustriesId.class)
public class JobsIndustriesDTO implements Serializable {
//    @Id
    private long industries_industryid;
//    @Id
    private long job_jobid;

}
