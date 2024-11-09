package com.HR.LeaveManagementSystem.services.imple;

import com.HR.LeaveManagementSystem.config.AppConstants;
import com.HR.LeaveManagementSystem.entities.Employee;
import com.HR.LeaveManagementSystem.entities.Role;
import com.HR.LeaveManagementSystem.exceptions.ResourceNotFoundException;
import com.HR.LeaveManagementSystem.payloads.EmployeeDto;
import com.HR.LeaveManagementSystem.repositories.EmployeeRepo;
import com.HR.LeaveManagementSystem.repositories.RoleRepo;
import com.HR.LeaveManagementSystem.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Cacheable("employee")
public class EmployeeServiceImple implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = this.empDtoToEmployee(employeeDto);
        Employee savedEmployee = this.employeeRepo.save(employee);
        return this.empToEmpDTO(savedEmployee);

    }

    @CachePut("employee")
    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Integer employeeId) {

        Employee employee =  this.employeeRepo.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee","Employee",employeeId));

        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setPassword(employeeDto.getPassword());
        employee.setManager(employeeDto.getManager());

        Employee updatedEmployee =this.employeeRepo.save(employee);

        return this.empToEmpDTO(updatedEmployee);
    }

    @CacheEvict("employee")
    @Override
    public void deleteEmployee(Integer employeeId) {
        Employee employee =  this.employeeRepo.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee","Employee",employeeId));
        this.employeeRepo.delete(employee);
    }

    @Override
    public EmployeeDto getEmployeeById(Integer employeeId) {
        Employee employee =  this.employeeRepo.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee","Employee",employeeId));
        return this.empToEmpDTO(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees =  this.employeeRepo.findAll();
        List<EmployeeDto> employeeDtos = employees.stream().map((employee) -> this.empToEmpDTO(employee)).collect(Collectors.toList());
        return employeeDtos;
    }

    @Override
    public List<EmployeeDto> getAllEmployeesByManager(String managerName) {
        List<Employee> employees =  this.employeeRepo.findByManagerName(managerName);
        List<EmployeeDto> employeeDtos = employees.stream().map((employee) -> this.empToEmpDTO(employee)).collect(Collectors.toList());
        return employeeDtos;
    }

    private EmployeeDto empToEmpDTO(Employee employee){
        return  this.modelMapper.map(employee,EmployeeDto.class);
    }

    private Employee empDtoToEmployee(EmployeeDto employeeDto){
        return  this.modelMapper.map(employeeDto,Employee.class);
    }

    @Override
    public EmployeeDto registerNewEmployee(EmployeeDto employeeDto) {

        Employee employee = this.empDtoToEmployee(employeeDto);
        employee.setPassword(this.passwordEncoder.encode(employee.getPassword()));

        Role role = this.roleRepo.findById(AppConstants.MANAGER).get();
        employee.getRoles().add(role);

        Employee savedEmployee = this.employeeRepo.save(employee);

        return this.empToEmpDTO(savedEmployee);
    }

}
