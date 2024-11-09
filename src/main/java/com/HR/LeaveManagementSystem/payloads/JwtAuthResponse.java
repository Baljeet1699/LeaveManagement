package com.HR.LeaveManagementSystem.payloads;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@jakarta.persistence.Cacheable
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JwtAuthResponse {

    private String token;
    private EmployeeDto employee;
}
