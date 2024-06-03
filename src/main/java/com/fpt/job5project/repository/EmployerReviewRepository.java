package com.fpt.job5project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.job5project.entity.EmployerReview;

public interface EmployerReviewRepository extends JpaRepository<EmployerReview, Long> {
    public EmployerReview findByCandidateIdAndEmployerId(long candidateId, long employerId);
}
