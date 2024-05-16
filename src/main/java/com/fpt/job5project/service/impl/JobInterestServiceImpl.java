package com.fpt.job5project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fpt.job5project.dto.JobInterestDTO;
import com.fpt.job5project.entity.JobInterest;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.mapper.JobInterestMapper;
import com.fpt.job5project.repository.JobInterestRepository;
import com.fpt.job5project.service.IJobInterestService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JobInterestServiceImpl implements IJobInterestService {

    JobInterestRepository jobInterestRepository;
    JobInterestMapper jobInterestMapper;

    @Override
    public List<JobInterestDTO> getJobInterest(long id) {
        List<JobInterestDTO> listDTOs = new ArrayList<>();
        List<JobInterest> listInterests = jobInterestRepository.findByCandidateId(id);
        // if (listInterests.isEmpty()) {
        // throw new AppException(ErrorCode.LIST_JOB_INTEREST_IS_NULL);
        // }
        for (JobInterest jobInterestEntity : listInterests) {
            listDTOs.add(jobInterestMapper.toDTO(jobInterestEntity));
        }
        return listDTOs;
    }

    @Override
    public JobInterestDTO addJobInterest(JobInterestDTO jobInterestDTO) {
        JobInterest jobInterestEntity = jobInterestMapper.toEntity(jobInterestDTO);
        jobInterestEntity.setInterestDate(new Date());
        return jobInterestMapper.toDTO(jobInterestRepository.save(jobInterestEntity));
    }

    @Override
    public void deleteJobInterest(long id) {
        boolean existsById = jobInterestRepository.existsById(id);
        if (!existsById) {
            throw new AppException(ErrorCode.JOB_INTEREST_NOT_EXIST);
        } else {
            jobInterestRepository.deleteById(id);
        }
    }
}
