package com.fpt.job5project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpt.job5project.entity.Employer;
import com.fpt.job5project.entity.User;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {

    public Employer findByEmployerName(String employerName);

    public boolean existsByEmailAndEmployerIdNot(String email, long id);

    public Employer findByEmail(String email);

    public Employer findByUser(User user);

    List<Employer> findByApprovedFalse();

    List<Employer> findByApprovedTrue();

}
