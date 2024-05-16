package com.fpt.job5project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fpt.job5project.dto.JobReportDTO;
import com.fpt.job5project.entity.JobReport;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.mapper.JobReportMapper;
import com.fpt.job5project.repository.JobReportRepository;
import com.fpt.job5project.service.IJobReportService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JobReportServiceImpl implements IJobReportService {

    JobReportRepository jobReportRepository;
    JobReportMapper jobReportMapper;

    @Override
    public List<JobReportDTO> listOfJobReports() {
        List<JobReportDTO> listDTOs = new ArrayList<>();
        List<JobReport> listEntities = jobReportRepository.findAll();
        if (listEntities.isEmpty()) {
            throw new AppException(ErrorCode.LIST_JOB_REPORT_IS_NULL);
        }
        for (JobReport jobReportEntity : jobReportRepository.findAll()) {
            listDTOs.add(jobReportMapper.toDTO(jobReportEntity));
        }
        return listDTOs;
    }

    @Override
    public JobReportDTO addJobReport(JobReportDTO jobReportDTO) {
        JobReport jobInterestEntity = jobReportMapper.toEntity(jobReportDTO);
        jobInterestEntity.setReportDate(new Date());
        return jobReportMapper.toDTO(jobReportRepository.save(jobInterestEntity));
    }

    @Override
    public void deleteJobReport(long id) {
        boolean existsById = jobReportRepository.existsById(id);
        if (!existsById) {
            throw new AppException(ErrorCode.JOB_REPORT_NOT_EXIST);
        } else {
            jobReportRepository.deleteById(id);
        }
    }
}
