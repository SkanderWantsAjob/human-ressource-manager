package com.HRM.HRmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.HRM.HRmanager.dto.EmployeeDto;
import com.HRM.HRmanager.info.EmployeeInfo;
import com.HRM.HRmanager.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        EmployeeDto employeeDto = employeeService.getEmployeeDtoById(id);
        return ResponseEntity.ok(employeeDto);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto createdEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeInfo> updateEmployee(@PathVariable Long id, @RequestBody EmployeeInfo employeeInfo) {
        EmployeeInfo updatedEmployee = employeeService.updateEmployee(id, employeeInfo);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
