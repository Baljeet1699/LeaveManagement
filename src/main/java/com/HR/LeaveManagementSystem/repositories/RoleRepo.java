package com.HR.LeaveManagementSystem.repositories;

import com.HR.LeaveManagementSystem.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
}
