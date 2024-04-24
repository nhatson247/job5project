package com.fpt.job5project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.job5project.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    /*
     * @Modifying
     * 
     * @Transactional
     * 
     * @Query(value = "EXEC CheckEmailExists :employerid, :email", nativeQuery =
     * true)
     * public int existsByEmail(@Param("employerid") long
     * employerid, @Param("email") String email);
     */

    public boolean existsByEmailAndCandidateIdNot(String email, long id);

}
