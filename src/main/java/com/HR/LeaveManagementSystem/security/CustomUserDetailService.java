package com.HR.LeaveManagementSystem.security;

import com.HR.LeaveManagementSystem.entities.Employee;
import com.HR.LeaveManagementSystem.exceptions.ResourceNotFoundException;
import com.HR.LeaveManagementSystem.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = this.employeeRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("Employee", "email" + username,0));
        return employee;
    }
}
