package com.fpt.job5project.repository;

import com.fpt.job5project.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    public List<Application> findByJobIdOrderByApplicationIdDesc(Long id);
    public List<Application> findByJobId(Long id);
    public List<Application> findByCandidateId(Long id);
    public Application findByCandidateIdAndJobId(Long candidateId, Long jobId);

}
