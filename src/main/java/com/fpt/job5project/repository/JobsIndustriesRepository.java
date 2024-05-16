package com.fpt.job5project.repository;

import com.fpt.job5project.Model.JobsIndustries;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobsIndustriesRepository extends JpaRepository<JobsIndustries, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO jobs_industries (industries_industryid, job_jobid) VALUES (:industryId, :jobId)", nativeQuery = true)
    void insertJobsIndustries(@Param("industryId") long industryId, @Param("jobId") long jobId);


    @Query(value = "if EXISTS (SELECT * FROM jobs_industries WHERE industries_industryid = :industryId AND job_jobid = :jobId) SELECT 1 ELSE BEGIN SELECT 0 END", nativeQuery = true)
    public int checkExists(@Param("industryId") long industryId, @Param("jobId") long jobId);

    @Query(value = "SELECT * FROM jobs_industries WHERE job_jobid = :jobId", nativeQuery = true)
    public List<JobsIndustries> findIndustriesByJobId(@Param("jobId") long jobId);

    @Modifying
    @Transactional
    @Query(value = "DELETE jobs_industries WHERE job_jobid = :jobId AND industries_industryid = :industryId", nativeQuery = true)
    public void deleteIndustryOfJob(@Param("jobId") long jobId, @Param("industryId") long industryId);

    @Modifying
    @Transactional
    @Query(value = "DELETE jobs_industries WHERE job_jobid = :jobId", nativeQuery = true)
    public void deleteIndustryByJobId(@Param("jobId") long jobId);

    @Modifying
    @Transactional
    @Query(value = "delete from jobs_industries where industries_industryid = :industryId", nativeQuery = true)
    public void deleteIndustryJobByIndustryId(@Param("industryId") long industryId);

}
