package com.fpt.job5project.repository;

import com.fpt.job5project.entity.JobReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobReportRepository extends JpaRepository<JobReport, Long> {
}
