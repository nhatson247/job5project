package com.fpt.job5project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fpt.job5project.dto.JobReportDTO;
import com.fpt.job5project.dto.JobReportDetailDTO;
import com.fpt.job5project.entity.JobReport;
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
    public List<JobReportDetailDTO> listOfJobReports() {
        List<Object[]> results = jobReportRepository.findAllJobReportDetails();
        List<JobReportDetailDTO> listDTOs = new ArrayList<>();
        for (Object[] result : results) {
            JobReportDetailDTO dto = new JobReportDetailDTO(
                    ((Number) result[0]).longValue(), // reportId
                    ((Number) result[1]).longValue(), // jobId
                    ((Number) result[2]).longValue(), // candidateId
                    (String) result[3], // candidateName
                    (Date) result[4], // reportDate
                    (String) result[5], // description
                    ((Number) result[6]).toString(), // employerId
                    (String) result[7] // employerName
            );
            listDTOs.add(dto);
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

        jobReportRepository.deleteJobReports(id);

    }

    @Override
    public void deleteJobReportID(long id) {

        jobReportRepository.deleteJobID(id);

    }

    @Override
    public boolean getReportByCandidateIdAndJobId(Long candidateId, Long jobId) {
        JobReport jobReport = jobReportRepository.findByCandidateIdAndJobId(candidateId, jobId);
        return jobReport != null;
    }
}
