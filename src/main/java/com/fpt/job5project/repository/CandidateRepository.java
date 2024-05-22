package com.fpt.job5project.repository;

import com.fpt.job5project.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    /*@Modifying
    @Transactional
    @Query(value = "EXEC CheckEmailExists :employerid, :email", nativeQuery = true)
    public int existsByEmail(@Param("employerid") long employerid, @Param("email") String email);*/

    public boolean existsByEmailAndCandidateIdNot(String email, long id);

    public Candidate findByEmail(String email);

    List<Candidate> findAllByCandidateIdIn(List<Long> ids);
}
