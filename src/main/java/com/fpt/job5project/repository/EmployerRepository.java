package com.fpt.job5project.repository;

import com.fpt.job5project.entity.Employer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {

    public Employer findByEmployerName(String employerName);

    public boolean existsByEmailAndEmployerIdNot(String email, long id);

//    @Modifying
//    @Transactional
//    @Query(value = "SELECT * FROM Employers WHERE employerId = :employerId", nativeQuery = true)
//    List<Employer> getById(@Param("employerId") long employerId);


//    @Modifying
//    @Transactional
//    @Query(value = "EXEC deleteUserByUserName :userName", nativeQuery = true)
//    void deleteUserByUserName(@Param("userName")String userName);
}
