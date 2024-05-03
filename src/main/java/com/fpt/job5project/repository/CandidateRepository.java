package com.fpt.job5project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.job5project.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    public boolean existsByEmailAndCandidateIdNot(String email, long id);

    public Candidate findByEmail(String email);

}
