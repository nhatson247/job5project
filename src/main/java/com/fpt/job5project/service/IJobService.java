package com.fpt.job5project.service;

import java.util.List;

import com.fpt.job5project.dto.JobDTO;

public interface IJobService {
    public JobDTO addJob(JobDTO newJob);

    public JobDTO updateJob(long id, JobDTO newJob);

    public List<JobDTO> getAllJob();

    public JobDTO getOneJob(long id);

    public List<JobDTO> getTopJobForHome(int numJob);
    public List<JobDTO> getJobByEmployer(long employerId);

    public List<JobDTO> resultSearchJob(long industryId, String searchValue, long minSalary, long maxSalary, String location);

}
