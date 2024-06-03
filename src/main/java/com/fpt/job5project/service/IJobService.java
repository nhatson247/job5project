package com.fpt.job5project.service;

import java.util.List;

import com.fpt.job5project.dto.JobDTO;
import com.fpt.job5project.dto.JobHomeDTO;

public interface IJobService {
    public JobDTO addJob(JobDTO newJob);

    public JobDTO updateJob(long id, JobDTO newJob);

    public List<JobDTO> getAllJob();

    public JobDTO getOneJob(long id);

    public List<JobHomeDTO> getTopJobForHome(int numJob);

    public List<JobDTO> getJobByEmployer(long employerId);

    public List<JobDTO> resultSearchJob(long industryId, String searchValue, long minSalary, long maxSalary,
            String location, int experience, int typeJob, int skip, int limit);

    public int updateIsExpired(long id);

    public int updateReup(long id);

    public int deleteJob(long id);

    public void hideJob(long jobId, long reportId, long employerId);

    public int numJobOfEmployer(long employerId);
}
