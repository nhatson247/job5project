package com.fpt.job5project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.fpt.job5project.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByUserName(String userName);

    Optional<User> findByUserName(String userName);

    @Query("SELECT u FROM User u LEFT JOIN u.employer e WHERE e.approved = true OR e IS NULL")
    List<User> findAllUsersFilteredByEmployerApproval();
}