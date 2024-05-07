package com.fpt.job5project.service;

import java.util.List;

import com.fpt.job5project.dto.JobBenefitDTO;

public interface IJobBenefitService {
    public JobBenefitDTO addJobBenefit(JobBenefitDTO newJob);

    public JobBenefitDTO updateJobBenefit(long id, JobBenefitDTO newJob);

    public List<JobBenefitDTO> getAllJobBenefit(long idJob);

    public void deleteJobBenefit(long id);

    public void deleteJobBenefitByJobId(long jobId);
}
