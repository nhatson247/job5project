package com.fpt.job5project.service.impl;

import com.fpt.job5project.dto.JobTypeDTO;
import com.fpt.job5project.entity.JobType;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.mapper.JobTypeMapper;
import com.fpt.job5project.repository.JobTypeRepository;
import com.fpt.job5project.service.IJobTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JobTypeServiceImpl implements IJobTypeService {
    JobTypeMapper jobTypeMapper;
    JobTypeRepository jobTypeRepository;

    @Override
    public JobTypeDTO addJobType(JobTypeDTO newJob) {
        JobType newJobType = new JobType();
        newJobType = jobTypeMapper.toEntity(newJob);
        // System.out.println("alo" + newJob.getType());
        return jobTypeMapper.toDTO(jobTypeRepository.save(newJobType));
    }

    @Override
    public JobTypeDTO updateJobType(int id, JobTypeDTO newJob) {
        JobType foundJob = jobTypeRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.JOB_NOT_EXIST));
        jobTypeMapper.updateJobType(foundJob, newJob);
        return jobTypeMapper.toDTO(jobTypeRepository.save(foundJob));
    }

    @Override
    public List<JobTypeDTO> getAllJobType() {
        List<JobTypeDTO> listDTO = new ArrayList<>();
        List<JobType> listEntity = jobTypeRepository.findAll();
        if (listEntity.isEmpty()) {
            throw new AppException(ErrorCode.LIST_JOBType_IS_NULL);
        }
        for (JobType a : listEntity) {
            listDTO.add(jobTypeMapper.toDTO(a));
        }
        return listDTO;
    }

    @Override
    public void deleteJobType(int id) {
        boolean existsById = jobTypeRepository.existsById(id);
        if (!existsById) {
            throw new AppException(ErrorCode.JOBType_NOT_EXIST);
        } else {
            jobTypeRepository.deleteById(id);
        }
    }
}