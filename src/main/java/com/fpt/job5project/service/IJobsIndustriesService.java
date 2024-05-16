package com.fpt.job5project.service;

import com.fpt.job5project.Model.JobsIndustries;

import java.util.List;

public interface IJobsIndustriesService {
    public void addJobsIndustries(JobsIndustries jobsIndustries);
    public List<JobsIndustries> getJobIndustriesByJobId(long jobId);

    public void deleteIndustryOfJob(long jobId, long industryId);

    public void deleteIndustriesByJobId(long jobId);

    public void deleteIndustryJobByIndustryId(long industryId);
}
