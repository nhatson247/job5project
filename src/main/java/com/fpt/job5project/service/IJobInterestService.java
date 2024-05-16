package com.fpt.job5project.service;

import java.util.List;

import com.fpt.job5project.dto.JobInterestDTO;

public interface IJobInterestService {
    // public List<JobInterestDTO> listOfJobInterest();

    public List<JobInterestDTO> getJobInterest(long id);

    public JobInterestDTO addJobInterest(JobInterestDTO jobInterestDTO);

    // public JobInterestDTO updateJobInterest(long id, JobInterestDTO candidateDTO,
    // MultipartFile file);

    public void deleteJobInterest(long id);
}
