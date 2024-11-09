package com.HR.LeaveManagementSystem.repositories;

import com.HR.LeaveManagementSystem.entities.AbsenceRequest;
import com.HR.LeaveManagementSystem.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AbsenceRequestRepo extends JpaRepository<AbsenceRequest,Integer> {

    @Query("select u from AbsenceRequest u where u.emp_id = :emp_id")
    List<AbsenceRequest> findByEmployeeId(Integer emp_id);

    @Query("select u from AbsenceRequest u where u.manager = :manager and is_approved='noAction'")
    List<AbsenceRequest> findByStatus(String manager);
}
