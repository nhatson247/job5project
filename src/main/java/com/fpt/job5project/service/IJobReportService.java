package com.fpt.job5project.service;

import java.util.List;

import com.fpt.job5project.dto.JobReportDTO;

public interface IJobReportService {
    public List<JobReportDTO> listOfJobReports();

    public JobReportDTO addJobReport(JobReportDTO jobReportDTO);

    public void deleteJobReport(long id);
}
