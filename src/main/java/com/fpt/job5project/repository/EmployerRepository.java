package com.fpt.job5project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpt.job5project.entity.Employer;
import com.fpt.job5project.entity.User;

import jakarta.transaction.Transactional;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {

    public Employer findByEmployerName(String employerName);

    public boolean existsByEmailAndEmployerIdNot(String email, long id);

    public boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE employers SET rankId = :rankId WHERE employerId = :employerId", nativeQuery = true)
    int updateRankById(@Param("rankId") long rankId, @Param("employerId") long employerId);

    public Employer findByEmail(String email);

    public Employer findByUser(User user);

    List<Employer> findByApprovedFalse();

    List<Employer> findByApprovedTrue();

    @Query("SELECT COUNT(DISTINCT e.employerId) FROM Employer e")
    long countDistinctEmployerIds();

    @Query(value = "EXEC getTopEmployers", nativeQuery = true)
    List<Employer> getTopEmployers();

    @Query(value = "SELECT * FROM employers where (:employerName = 'null' or :employerName is null or :employerName = '0' or :employerName = N'' or employername like N'%'+:employerName+'%') ORDER BY CASE WHEN rankid = 4 THEN 1 WHEN rankid = 3 THEN 2 WHEN rankid = 2 THEN 3 WHEN rankid = 1 THEN 4 END OFFSET :skip ROWS FETCH NEXT :limit ROWS ONLY", nativeQuery = true)
    List<Employer> resultSearch(@Param("employerName") String employerName, @Param("skip") int skip,
            @Param("limit") int limit);
}
