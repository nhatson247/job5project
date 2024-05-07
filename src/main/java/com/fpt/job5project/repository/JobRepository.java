package com.fpt.job5project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.job5project.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

}
