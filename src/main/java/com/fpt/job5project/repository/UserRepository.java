package com.fpt.job5project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fpt.job5project.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByUserName(String userName);

    Optional<User> findByUserName(String userName);
}