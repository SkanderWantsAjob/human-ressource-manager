package com.HRM.HRmanager.controller;

import com.HRM.HRmanager.dto.EmployeeDto;
import com.HRM.HRmanager.model.Employee;
import com.HRM.HRmanager.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
     // ANSI color codes
     String ANSI_RED = "\u001B[31m";
     String ANSI_RESET = "\u001B[0m";
     
    private final EmployeeService employeeService;

    public UserController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/me")
    public EmployeeDto authenticatedUser() {
        System.err.println(ANSI_RED + "This is a debug message of ( it started the users/me endpoint)" + ANSI_RESET);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.err.println(ANSI_RED + "This is a debug message of authenticate2" +authentication + ANSI_RESET);

        UserDetails currentUser = (UserDetails) authentication.getPrincipal();
        EmployeeDto dto = employeeService.createDto((Employee)currentUser);
        System.err.println(ANSI_RED + "This is a debug message of authenticate3" + dto +  ANSI_RESET);
        System.err.println();
        return dto;

    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDto>> allUsers() {
        List <EmployeeDto> users = employeeService.getAllEmployees();

        return ResponseEntity.ok(users);
    }
}