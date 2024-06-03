package com.fpt.job5project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpt.job5project.entity.JobReport;

import jakarta.transaction.Transactional;

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

    public JobReport findByCandidateIdAndJobId(Long candidateId, Long jobId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM jobreports WHERE reportid = :reportId", nativeQuery = true)
    int deleteJobID(@Param("reportId") long reportId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM jobreports WHERE jobid = :jobId", nativeQuery = true)
    int deleteJobReports(@Param("jobId") long jobId);
}
