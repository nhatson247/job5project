package com.fpt.job5project.repository;

import com.fpt.job5project.entity.Industry;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IndustryRepository extends JpaRepository<Industry, Long> {
    @Modifying
        @Transactional
        @Query(value = "SELECT ind.industryname, COUNT(j.jobid) AS TotalJobs " +
                        "FROM industries ind " +
                        "LEFT JOIN jobs_industries ji ON ji.industries_industryid  = ind.industryid " +
                        "LEFT JOIN jobs j ON ji.job_jobid = j.jobid " +
                        "GROUP BY ind.industryname " +
                        "ORDER BY TotalJobs DESC", nativeQuery = true)
        List<Object[]> findTopIndustriesByJobCount();

    @Modifying
    @Transactional
    @Query(value = "SELECT TOP(:topCount) ind.industryname, COUNT(ap.candidateid) AS TotalCandidates " +
            "FROM industries ind " +
            "LEFT JOIN jobs_industries ji ON ji.industries_industryid  = ind.industryid " +
            "LEFT JOIN jobs j ON ji.job_jobid = j.jobid " +
            "LEFT JOIN applications ap ON j.jobid = ap.jobid " +
            "GROUP BY ind.industryname " +
            "ORDER BY TotalCandidates DESC", nativeQuery = true)
    List<Object[]> findTopIndustriesByJobApplicationCount(@Param("topCount") int topCount);
}
