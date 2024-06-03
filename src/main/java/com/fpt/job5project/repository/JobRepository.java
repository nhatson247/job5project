package com.fpt.job5project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpt.job5project.entity.Job;

import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    @Query(value = "EXEC GetJobsByEmployer @numJobsToShow= :numJobs", nativeQuery = true)
    List<Tuple> getTopJobForHome(@Param("numJobs") int numJobs);

    @Query(value = "SELECT * FROM jobs WHERE employerId = :employerId ORDER BY postdate DESC", nativeQuery = true)
    public List<Job> findByEmployerId(@Param("employerId") long employerId);

    @Query(value = "EXEC ResultSeachSQL @industryId = :industryId, @searchValue = :searchValue, @minSalary = :minSalary, @maxSalary = :maxSalary, @location =  :location, @experience = :experience, @typeJob = :typeJob, @skip = :skip, @limit = :limit", nativeQuery = true)
    public List<Job> resultSearch(@Param("industryId") long industryId, @Param("searchValue") String searchValue,
            @Param("minSalary") long minSalary, @Param("maxSalary") long maxSalary, @Param("location") String location,
            @Param("experience") int experience, @Param("typeJob") int typeJob, @Param("skip") int skip,
            @Param("limit") int limit);

    @Modifying
    @Transactional
    @Query(value = "UPDATE jobs SET ExpirationDate = CAST(CAST(GETDATE() AS date) AS datetime) WHERE jobId = :jobId AND ExpirationDate > CAST(GETDATE() AS date)", nativeQuery = true)
    int updateIsExpired(@Param("jobId") long jobId);

    @Modifying
    @Transactional
    @Query(value = "EXEC UpdateJobReup @jobId = :jobId", nativeQuery = true)
    int updateReup(@Param("jobId") long jobId);

    @Modifying
    @Transactional
    @Query(value = "EXEC DeleteJobsAndIndustries @jobId = :jobId", nativeQuery = true)
    int deleteJob(@Param("jobId") long jobId);

    // long countByExpiredFalse();

    @Modifying
    @Transactional
    @Query(value = "SELECT TOP(:topCount) j.location, COUNT(j.jobid) AS TotalJobs " +
            "FROM jobs j " +
            "GROUP BY j.location " +
            "ORDER BY TotalJobs DESC", nativeQuery = true)
    List<Object[]> findTopLocationJobCount(@Param("topCount") int topCount);

    @Modifying
    @Transactional
    @Query(value = "UPDATE jobs SET removed = 1 WHERE jobid = :jobId", nativeQuery = true)
    int hideJob(@Param("jobId") long jobId);

    @Transactional
    @Query(value = "SELECT COUNT(j.jobid) FROM jobs j", nativeQuery = true)
    long countTotalJobs();

    @Query(value = "SELECT COUNT(*) FROM jobs j WHERE j.employerid = :employerId", nativeQuery = true)
    int NumJobOfEmployer(@Param("employerId") long employerId);
}
