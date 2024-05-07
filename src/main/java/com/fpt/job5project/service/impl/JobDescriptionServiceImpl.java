package com.fpt.job5project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fpt.job5project.dto.JobDescriptionDTO;
import com.fpt.job5project.entity.JobDescription;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.mapper.JobDescriptionMapper;
import com.fpt.job5project.repository.JobDescriptionRepository;
import com.fpt.job5project.service.IJobDescriptionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JobDescriptionServiceImpl implements IJobDescriptionService {
    JobDescriptionMapper jobDescriptionMapper;
    JobDescriptionRepository jobDescriptionRepository;

    @Override
    public JobDescriptionDTO addJobDescription(JobDescriptionDTO newJob) {
        JobDescription newJobDescription = new JobDescription();
        newJobDescription = jobDescriptionMapper.toEntity(newJob);
        return jobDescriptionMapper.toDTO(jobDescriptionRepository.save(newJobDescription));
    }

    @Override
    public JobDescriptionDTO updateJobDescription(long id, JobDescriptionDTO newJob) {
        JobDescription foundJob = jobDescriptionRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.JOB_NOT_EXIST));
        jobDescriptionMapper.updateJobDescription(foundJob, newJob);
        return jobDescriptionMapper.toDTO(jobDescriptionRepository.save(foundJob));
    }

    @Override
    public List<JobDescriptionDTO> getAllJobDescription(long idJob) {
        List<JobDescriptionDTO> listDTO = new ArrayList<>();
        List<JobDescription> listEntity = jobDescriptionRepository.findAllByJobId(idJob);
        if (listEntity.isEmpty()) {
            throw new AppException(ErrorCode.LIST_JOBDescription_IS_NULL);
        }
        for (JobDescription a : listEntity) {
            listDTO.add(jobDescriptionMapper.toDTO(a));
        }
        return listDTO;
    }

    @Override
    public void deleteJobDescription(long id) {
        boolean existsById = jobDescriptionRepository.existsById(id);
        if (!existsById) {
            throw new AppException(ErrorCode.JOBDescription_NOT_EXIST);
        } else {
            jobDescriptionRepository.deleteById(id);
        }
    }

    @Override
    public void deleteJobDescriptionByJobId(long jobId) {

        jobDescriptionRepository.deleteDescriptionByJobId(jobId);
    }
}
