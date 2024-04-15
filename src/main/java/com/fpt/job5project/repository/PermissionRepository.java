package com.fpt.job5project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpt.job5project.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
}