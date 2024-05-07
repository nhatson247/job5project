package com.fpt.job5project.service;

import java.util.List;

import com.fpt.job5project.dto.JobTypeDTO;

public interface IJobTypeService {
    public JobTypeDTO addJobType(JobTypeDTO newJob);

    public JobTypeDTO updateJobType(int id, JobTypeDTO newJob);

    public List<JobTypeDTO> getAllJobType();

    public void deleteJobType(int id);
}
