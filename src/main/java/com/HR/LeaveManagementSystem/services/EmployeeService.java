package com.HR.LeaveManagementSystem.services;

import com.HR.LeaveManagementSystem.payloads.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto registerNewEmployee(EmployeeDto employeeDto);

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto updateEmployee(EmployeeDto employeeDto, Integer employeeId);

    void deleteEmployee(Integer employeeId);

    EmployeeDto getEmployeeById(Integer employeeId);

    List<EmployeeDto> getAllEmployees();

    List<EmployeeDto> getAllEmployeesByManager(String managerName);

}
