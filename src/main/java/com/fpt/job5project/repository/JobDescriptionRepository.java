package com.fpt.job5project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpt.job5project.entity.JobDescription;

import jakarta.transaction.Transactional;

public interface JobDescriptionRepository extends JpaRepository<JobDescription, Long> {
    public List<JobDescription> findAllByJobId(long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE jobdescriptions WHERE jobid = :jobId", nativeQuery = true)
    public void deleteDescriptionByJobId(@Param("jobId") long jobId);
}
