package com.fpt.job5project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpt.job5project.entity.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {

    public Employer findByEmployerName(String employerName);

    public boolean existsByEmailAndEmployerIdNot(String email, long id);

    // @Modifying
    // @Transactional
    // @Query(value = "SELECT * FROM Employers WHERE employerId = :employerId",
    // nativeQuery = true)
    // List<Employer> getById(@Param("employerId") long employerId);

    // @Modifying
    // @Transactional
    // @Query(value = "EXEC deleteUserByUserName :userName", nativeQuery = true)
    // void deleteUserByUserName(@Param("userName")String userName);
}
