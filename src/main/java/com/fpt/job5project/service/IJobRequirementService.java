package com.fpt.job5project.service;

import java.util.List;

import com.fpt.job5project.dto.JobRequirementDTO;

public interface IJobRequirementService {
    public JobRequirementDTO addJobRequirement(JobRequirementDTO newJob);

    public JobRequirementDTO updateJobRequirement(long id, JobRequirementDTO newJob);

    public List<JobRequirementDTO> getAllJobRequirement(long idJob);

    public void deleteJobRequirement(long id);

    public void deleteJobRequirementByJobId(long idJob);
}
