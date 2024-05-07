package com.fpt.job5project.repository;

import com.fpt.job5project.entity.JobRequirement;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRequirementRepository extends JpaRepository<JobRequirement, Long> {
    public List<JobRequirement> findAllByJobId(long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE jobrequirements WHERE jobid = :jobId", nativeQuery = true)
    public void deleteRequirementByJobId(@Param("jobId") long jobId);
}
