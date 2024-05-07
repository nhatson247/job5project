package com.fpt.job5project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fpt.job5project.dto.JobBenefitDTO;
import com.fpt.job5project.entity.JobBenefit;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.mapper.JobBenefitMapper;
import com.fpt.job5project.repository.JobBenefitRepository;
import com.fpt.job5project.service.IJobBenefitService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JobBenefitServiceImpl implements IJobBenefitService {
    JobBenefitRepository jobBenefitRepository;
    JobBenefitMapper jobBenefitMapper;

    @Override
    public JobBenefitDTO addJobBenefit(JobBenefitDTO newJobDTO) {
        JobBenefit newJobBenefit = new JobBenefit();
        newJobBenefit = jobBenefitMapper.toEntity(newJobDTO);
        return jobBenefitMapper.toDTO(jobBenefitRepository.save(newJobBenefit));
    }

    @Override
    public JobBenefitDTO updateJobBenefit(long id, JobBenefitDTO newJob) {
        JobBenefit foundJob = jobBenefitRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.JOB_NOT_EXIST));
        jobBenefitMapper.updateJobBenefit(foundJob, newJob);
        return jobBenefitMapper.toDTO(jobBenefitRepository.save(foundJob));
    }

    @Override
    public List<JobBenefitDTO> getAllJobBenefit(long idJob) {
        List<JobBenefitDTO> listDTO = new ArrayList<>();
        List<JobBenefit> listEntity = jobBenefitRepository.findAllByJobId(idJob);
        if (listEntity.isEmpty()) {
            throw new AppException(ErrorCode.LIST_JOBBENEFIT_IS_NULL);
        }
        for (JobBenefit a : listEntity) {
            listDTO.add(jobBenefitMapper.toDTO(a));
        }
        return listDTO;
    }

    @Override
    public void deleteJobBenefit(long id) {
        boolean existsById = jobBenefitRepository.existsById(id);
        if (!existsById) {
            throw new AppException(ErrorCode.JOBBENEFIT_NOT_EXIST);
        } else {
            jobBenefitRepository.deleteById(id);
        }
    }

    @Override
    public void deleteJobBenefitByJobId(long jobId) {
        jobBenefitRepository.deleteBenenfitByJobId(jobId);
    }
}
