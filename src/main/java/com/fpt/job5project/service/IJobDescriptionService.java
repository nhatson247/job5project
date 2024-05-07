package com.fpt.job5project.service;

import java.util.List;

import com.fpt.job5project.dto.JobDescriptionDTO;

public interface IJobDescriptionService {
    public JobDescriptionDTO addJobDescription(JobDescriptionDTO newJob);

    public JobDescriptionDTO updateJobDescription(long id, JobDescriptionDTO newJob);

    public List<JobDescriptionDTO> getAllJobDescription(long idJob);

    public void deleteJobDescription(long id);

    public void deleteJobDescriptionByJobId(long jobId);
}
