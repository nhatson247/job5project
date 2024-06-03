package com.fpt.job5project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.job5project.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    public List<Application> findByJobIdOrderByApplicationIdDesc(Long id);

    public List<Application> findByJobId(Long id);

    public List<Application> findByCandidateId(Long id);

    public Application findByCandidateIdAndJobId(Long candidateId, Long jobId);

}
