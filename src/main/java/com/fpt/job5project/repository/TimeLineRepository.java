package com.fpt.job5project.repository;

import com.fpt.job5project.entity.TimeLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface TimeLineRepository extends JpaRepository<TimeLine, Long> {

    @Modifying
    @Transactional
    @Query(value = "select * from timelines where candidateid = :id", nativeQuery = true)
    public List<TimeLine> findByCandidateId(Long id);
}
