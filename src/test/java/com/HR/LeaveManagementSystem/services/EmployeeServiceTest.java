package com.HR.LeaveManagementSystem.services;

import com.HR.LeaveManagementSystem.entities.Employee;
import com.HR.LeaveManagementSystem.entities.Role;
import com.HR.LeaveManagementSystem.payloads.EmployeeDto;
import com.HR.LeaveManagementSystem.repositories.EmployeeRepo;
import com.HR.LeaveManagementSystem.repositories.RoleRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@SpringBootTest
public class EmployeeServiceTest {

   @MockBean
   private EmployeeRepo employeeRepo;

   @MockBean
   private RoleRepo roleRepo;

   @Autowired
   private EmployeeService employeeService;

   @Autowired
   private ModelMapper modelMapper;

   Employee employee;
   Role role;
   int roleId;

   @BeforeEach
   public void init(){

      role = Role.builder()
               .id(1).name("EMPLOYEE")
                       .build();

      employee = Employee.builder()
                .name("Daljinder Singh")
                .email("daljinder@gmail.com")
                .password("123")
                .manager("Amrik Singh")
                .roles(Set.of(role))
                .build();

   }

   @Test
   public void registerNewEmployeeTest(){

      Mockito.when(employeeRepo.save(Mockito.any())).thenReturn(employee);
      Mockito.when(roleRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(role));

      EmployeeDto employeeDto = employeeService.registerNewEmployee(modelMapper.map(employee, EmployeeDto.class));

      Assertions.assertNotNull(employeeDto);

   }


}
