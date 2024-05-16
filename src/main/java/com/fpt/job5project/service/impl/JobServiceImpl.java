package com.fpt.job5project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fpt.job5project.dto.JobDTO;
import com.fpt.job5project.entity.Job;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.mapper.JobMapper;
import com.fpt.job5project.repository.JobRepository;
import com.fpt.job5project.service.IJobService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JobServiceImpl implements IJobService {
    JobRepository jobRepository;
    JobMapper jobMapper;

    @Override
    public JobDTO addJob(JobDTO newJobDTO) {

        Job newJob = new Job();
        newJob = jobMapper.toEntity(newJobDTO);
        return jobMapper.toDTO(jobRepository.save(newJob));
    }

    @Override
    public JobDTO updateJob(long id, JobDTO newJob) {
        Job foundJob = jobRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CANDIDATE_NOT_EXIST));
        jobMapper.updateJob(foundJob, newJob);
        return jobMapper.toDTO(jobRepository.save(foundJob));
    }

    @Override
    public List<JobDTO> getAllJob() {
        List<JobDTO> listDTO = new ArrayList<>();
        List<Job> listEntity = jobRepository.findAll();
        if (listEntity.isEmpty()) {
            throw new AppException(ErrorCode.LIST_CANDIDATE_IS_NULL);
        }
        for (Job a : listEntity) {
            listDTO.add(jobMapper.toDTO(a));
        }
        return listDTO;
    }

    public JobDTO getOneJob(long id) {
        Job foundJob = jobRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CANDIDATE_NOT_EXIST));
        return jobMapper.toDTO(foundJob);
    }

    @Override
    public List<JobDTO> getTopJobForHome(int numJobs) {
        List<JobDTO> listDTO = new ArrayList<>();
        List<Job> listEntity = jobRepository.getTopJobForHome(numJobs);
        for (Job a : listEntity) {
            listDTO.add(jobMapper.toDTO(a));
        }
        return listDTO;
    }

    @Override
    public List<JobDTO> getJobByEmployer(long employerId) {
        List<JobDTO> listDTO = new ArrayList<>();
        List<Job> listEntity = jobRepository.findByEmployerId(employerId);
        for(Job a : listEntity){
            listDTO.add(jobMapper.toDTO(a));
        }
        return listDTO;
    }

    @Override
    public List<JobDTO> resultSearchJob(long industryId, String searchValue, long minSalary, long maxSalary, String location) {
        List<JobDTO> listDTO = new ArrayList<>();
        List<Job> listEntity = jobRepository.resultSearch(industryId, searchValue, minSalary, maxSalary, location);
        for (Job a : listEntity) {
            listDTO.add(jobMapper.toDTO(a));
        }
        return listDTO;
    }

}
