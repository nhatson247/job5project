package com.fpt.job5project.repository;

import com.fpt.job5project.entity.JobReport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobReportRepository extends JpaRepository<JobReport, Long> {
    @Query(value = "SELECT jr.reportid AS reportId, j.jobid AS jobId, c.candidateid AS candidateId, c.fullname AS fullname, "
            +
            "jr.reportdate AS reportDate, jr.description AS description, e.employerid AS employerId, e.employername AS employerName "
            +
            "FROM jobreports jr " +
            "INNER JOIN candidates c ON jr.candidateid = c.candidateid " +
            "INNER JOIN jobs j ON jr.jobid = j.jobid " +
            "INNER JOIN employers e ON j.employerid = e.employerid", nativeQuery = true)
    List<Object[]> findAllJobReportDetails();
}
