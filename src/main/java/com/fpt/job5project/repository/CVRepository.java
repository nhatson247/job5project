package com.fpt.job5project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.job5project.entity.CV;

public interface CVRepository extends JpaRepository<CV, Long> {
    // @Modifying
    // @Transactional
    // @Query(value = "select * from cvs where candidateid = :id", nativeQuery =
    // true)
    public List<CV> findByCandidateId(Long id);

}
