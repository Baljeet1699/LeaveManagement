package com.HR.LeaveManagementSystem.repositories;

import com.HR.LeaveManagementSystem.entities.AbsenceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceTypeRepo extends JpaRepository<AbsenceType,Integer> {

}
