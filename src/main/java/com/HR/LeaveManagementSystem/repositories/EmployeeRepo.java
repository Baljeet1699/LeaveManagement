package com.HR.LeaveManagementSystem.repositories;

import com.HR.LeaveManagementSystem.entities.AbsenceRequest;
import com.HR.LeaveManagementSystem.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

    Optional<Employee> findByEmail(String email);

    @Query("select u from Employee u where u.manager = :manager")
    List<Employee> findByManagerName(String manager);
}
