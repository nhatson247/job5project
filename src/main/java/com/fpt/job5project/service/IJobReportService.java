package com.fpt.job5project.service;

import java.util.List;

import com.fpt.job5project.dto.JobReportDTO;
import com.fpt.job5project.dto.JobReportDetailDTO;

public interface IJobReportService {
    public List<JobReportDetailDTO> listOfJobReports();

    public JobReportDTO addJobReport(JobReportDTO jobReportDTO);

    public void deleteJobReportID(long id);

    public void deleteJobReport(long id);

    public boolean getReportByCandidateIdAndJobId(Long candidateId, Long jobId);
}
