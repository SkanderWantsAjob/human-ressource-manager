package com.HRM.HRmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HRM.HRmanager.dto.EmployeeDto;
import com.HRM.HRmanager.info.EmployeeInfo;
import com.HRM.HRmanager.info.ProjectInfo;
import com.HRM.HRmanager.model.Employee;
import com.HRM.HRmanager.model.Project;
import com.HRM.HRmanager.repository.EmployeeRepository;


import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectService projectService;

   public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee : employees) {
            employeeDtos.add(createDto(employee));
        }
        return employeeDtos;
    }
    @Transactional(readOnly = true)
    public EmployeeDto getEmployeeDtoById(Long id) {
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));
        return createDto(employee);
    }

    @Transactional
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = createEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return createDto(savedEmployee);
    }

    @Transactional
    public EmployeeInfo updateEmployee(Long id, EmployeeInfo employeeInfo) {
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setFullName(employeeInfo.getFullName());
        employee.setEmail(employee.getEmail());
        employee.setDesignation(employeeInfo.getDesignation());
        employee.setSalary(employeeInfo.getSalary());
        Employee updatedEmployee = employeeRepository.save(employee);
        return createInfo(updatedEmployee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee createEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setFullName(employeeDto.getFullName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDesignation(employeeDto.getDesignation());
        employee.setSalary(employeeDto.getSalary());
        employee.setDepartment(employeeDto.getDepartment());

        // Convert ProjectInfo to Project entities
        List<Project> projects = employeeDto.getProjects().stream()
            .map(projectInfo -> {
                Project project = projectService.findOr404(projectInfo.getId());
                employee.addProject(project);
                return project;
            })
            .toList();
        
        return employee;
    }

    public EmployeeDto createDto(Employee employee) {
    
        
        // Define ANSI color codes
        String ANSI_RED = "\u001B[31m";
        String ANSI_RESET = "\u001B[0m";
        
        // Print an example error message in red
        System.err.println(ANSI_RED + "CREATING EMPLOYEE DTO" + ANSI_RESET);
        
        // Create the EmployeeDto object
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        System.out.println("ffff" + employee.getId());
        employeeDto.setFullName(employee.getFullName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setDepartment(employee.getDepartment());
        employeeDto.setDesignation(employee.getDesignation());
        employeeDto.setSalary(employee.getSalary());
        System.err.println(employeeDto);
        
        // Check and handle null values for projects
        if (employee.getProjects().size()== 0) {
            
            System.err.println(ANSI_RED + "No projects found for the employee." + ANSI_RESET);
            
        } else {
            List<ProjectInfo> projectInfos = new ArrayList<>();
            for (Project project : employee.getProjects()) {
                ProjectInfo projectInfo = projectService.createInfo(project);
                projectInfos.add(projectInfo);
            }
            employeeDto.setProjects(projectInfos);
        }
        System.err.println(ANSI_RED + employeeDto  + "bob" + ANSI_RESET);
        return employeeDto;
    }
    
    public EmployeeInfo createInfo(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        EmployeeInfo employeeInfo = new EmployeeInfo();
        employeeInfo.setId(employee.getId());
        employeeInfo.setFullName(employee.getFullName());
        employeeInfo.setDesignation(employee.getDesignation());
        employeeInfo.setDesignation(employee.getDesignation()); 
        employeeInfo.setSalary(employee.getSalary());
        return employeeInfo;
    }

    // Utility method to find an Employee by ID or throw exception
    public Employee findOr404(Long id) {
        return employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}
