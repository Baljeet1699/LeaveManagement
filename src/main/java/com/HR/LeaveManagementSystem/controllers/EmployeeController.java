package com.HR.LeaveManagementSystem.controllers;

import com.HR.LeaveManagementSystem.payloads.AbsenceRequestDto;
import com.HR.LeaveManagementSystem.payloads.AbsenceTypeDto;
import com.HR.LeaveManagementSystem.payloads.EmployeeDto;
import com.HR.LeaveManagementSystem.payloads.EventDto;
import com.HR.LeaveManagementSystem.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/Employees")
@CrossOrigin(origins="*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{manager}")
    public ResponseEntity<List<EmployeeDto>> getAbsenceRequestByEmpId(@PathVariable String  manager){
        List<EmployeeDto> employeeDtos =  this.employeeService.getAllEmployeesByManager(manager);
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }
}
