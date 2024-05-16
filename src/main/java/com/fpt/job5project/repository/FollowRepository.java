package com.fpt.job5project.repository;

import com.fpt.job5project.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository  extends JpaRepository<Follow,Long> {
    public List<Follow> findByCandidateId(Long id);

}
