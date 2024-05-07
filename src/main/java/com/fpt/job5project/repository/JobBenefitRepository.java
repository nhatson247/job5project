package com.fpt.job5project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpt.job5project.entity.JobBenefit;

import jakarta.transaction.Transactional;

public interface JobBenefitRepository extends JpaRepository<JobBenefit, Long> {
    public List<JobBenefit> findAllByJobId(long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE jobbenefits WHERE jobid = :jobId", nativeQuery = true)
    public void deleteBenenfitByJobId(@Param("jobId") long jobId);
}
