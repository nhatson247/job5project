package com.fpt.job5project.service.impl;

import com.fpt.job5project.dto.JobRequirementDTO;
import com.fpt.job5project.entity.JobRequirement;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.mapper.JobRequirementMapper;
import com.fpt.job5project.repository.JobRequirementRepository;
import com.fpt.job5project.service.IJobRequirementService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JobRequirementServiceImpl implements IJobRequirementService {
    JobRequirementMapper jobRequirementMapper;
    JobRequirementRepository jobRequirementRepository;
    @Override
    public JobRequirementDTO addJobRequirement(JobRequirementDTO newJob) {
        JobRequirement newJobRequirement = new JobRequirement();
        newJobRequirement = jobRequirementMapper.toEntity(newJob);
        return jobRequirementMapper.toDTO(jobRequirementRepository.save(newJobRequirement));
    }

    @Override
    public JobRequirementDTO updateJobRequirement(long id, JobRequirementDTO newJob) {
        JobRequirement foundJob = jobRequirementRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.JOB_NOT_EXIST));
        jobRequirementMapper.updateJobRequirement(foundJob, newJob);
        return jobRequirementMapper.toDTO(jobRequirementRepository.save(foundJob));
    }

    @Override
    public List<JobRequirementDTO> getAllJobRequirement(long idJob) {
        List<JobRequirementDTO> listDTO = new ArrayList<JobRequirementDTO>();
        List<JobRequirement> listEntity = jobRequirementRepository.findAllByJobId(idJob);
        if(listEntity.isEmpty()){
            throw new AppException(ErrorCode.LIST_JOBRequirement_IS_NULL);
        }
        for(JobRequirement a : listEntity){
            listDTO.add(jobRequirementMapper.toDTO(a));
        }
        return listDTO;
    }

    @Override
    public void deleteJobRequirement(long id) {
        boolean existsById = jobRequirementRepository.existsById(id);
        if (!existsById) {
            throw new AppException(ErrorCode.JOBRequirement_NOT_EXIST);
        } else {
            jobRequirementRepository.deleteById(id);
        }
    }

    @Override
    public void deleteJobRequirementByJobId(long idJob) {
        jobRequirementRepository.deleteRequirementByJobId(idJob);
    }
}