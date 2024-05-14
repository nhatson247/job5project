package com.fpt.job5project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpt.job5project.entity.Job;

import jakarta.transaction.Transactional;

public interface JobRepository extends JpaRepository<Job, Long> {
    long countByIsExpiredFalse();

    @Modifying
    @Transactional
    @Query(value = "SELECT TOP(:topCount) j.location, COUNT(j.jobid) AS TotalJobs " +
            "FROM jobs j " +
            "GROUP BY j.location " +
            "ORDER BY TotalJobs DESC", nativeQuery = true)
    List<Object[]> findTopLocationJobCount(@Param("topCount") int topCount);
}
