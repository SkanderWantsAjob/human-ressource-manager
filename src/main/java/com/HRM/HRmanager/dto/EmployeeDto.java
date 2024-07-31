package com.HRM.HRmanager.dto;

import java.util.ArrayList;
import java.util.List;

import com.HRM.HRmanager.info.ProjectInfo;
import com.HRM.HRmanager.model.Department;

public class EmployeeDto {
    private Long id;
    private String fullName;
    private String email;
    private String designation;
    private double salary;
    private Department department;
   
    private List<ProjectInfo> projects = new ArrayList<>();


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

   
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    public List<ProjectInfo> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectInfo> projects) {
        this.projects = projects;
    }
}
