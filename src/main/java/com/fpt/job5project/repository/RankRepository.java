package com.fpt.job5project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpt.job5project.entity.Rank;

import jakarta.transaction.Transactional;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {

    @Modifying
    @Transactional
    @Query(value = "SELECT r.rankname, SUM(r.price)" +
            "FROM ranks r " +
            "INNER JOIN employers e ON e.rankid = r.rankid " +
            "GROUP BY r.rankname", nativeQuery = true)
    List<Object[]> getRankPrices();
}