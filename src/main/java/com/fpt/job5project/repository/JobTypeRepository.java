package com.fpt.job5project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.job5project.entity.JobType;

public interface JobTypeRepository extends JpaRepository<JobType, Integer> {
}
