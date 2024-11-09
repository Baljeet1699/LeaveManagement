package com.HR.LeaveManagementSystem.payloads;

import com.HR.LeaveManagementSystem.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@jakarta.persistence.Cacheable
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmployeeDto {

    private int id;
    private String name;
    private String email;
    private String password;
    private String manager;
    private Set<RoleDto> roles = new HashSet<>();
}
