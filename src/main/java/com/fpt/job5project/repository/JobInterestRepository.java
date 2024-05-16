package com.fpt.job5project.repository;

import com.fpt.job5project.entity.JobInterest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobInterestRepository extends JpaRepository<JobInterest, Long> {
    public List<JobInterest> findByCandidateId(Long id);
}
