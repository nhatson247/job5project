package com.fpt.job5project.service;

import com.fpt.job5project.dto.CandidateDTO;
import com.fpt.job5project.dto.JobInterestDTO;
import com.fpt.job5project.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IJobInterestService {
//    public List<JobInterestDTO> listOfJobInterest();

    public List<JobInterestDTO> getJobInterest(long id);

    public List<JobInterestDTO> getJobInterestByCandidateIdAndByJobId(Long candidateId, Long jobId);

    public JobInterestDTO addJobInterest(JobInterestDTO jobInterestDTO);

//    public JobInterestDTO updateJobInterest(long id, JobInterestDTO candidateDTO, MultipartFile file);

    public void deleteJobInterest(long id);
}
