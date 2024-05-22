package com.fpt.job5project.service;

import com.fpt.job5project.dto.JobsIndustriesDTO;
import com.fpt.job5project.dto.QuantityJobDTO;

import java.util.List;

public interface IJobsIndustriesService {
    public void addJobsIndustries(JobsIndustriesDTO jobsIndustries);
    public List<JobsIndustriesDTO> getJobIndustriesByJobId(long jobId);

    public void deleteIndustryOfJob(long jobId, long industryId);

    public void deleteIndustriesByJobId(long jobId);

    public List<QuantityJobDTO> quantityJobOfIndustryId();

    public void deleteIndustryJobByIndustryId(long industryId);
}
