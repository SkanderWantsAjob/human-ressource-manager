package com.HRM.HRmanager.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Employee implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fullName;


    @Column
   
    private String email;

    private String password;

    @Column
    private String designation;

    @Column
    private double salary;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Department department;

    @OneToMany(mappedBy = "headOfProject", cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.EAGER)
    
    private List<Project> projects = new ArrayList<>();

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
    



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return password;
    }

 

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Getters and Setters

    public String getUsername() {
        return this.email;
    }
    

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
        return email;
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
    public List<Project> getProjects() {
        if(this.projects==null){
            return new ArrayList<>();
        }
        return projects;
    }

  public void addProject(Project project) {
        if (projects == null) {
            projects = new ArrayList<>();
        }
        projects.add(project);
        project.setHeadOfProject(this);
    }
    public void removeProject(Project project) {
        if (projects != null) {
            projects.remove(project);
            project.setHeadOfProject(null);
        }
    }
   
    public void setPassword(String Password){
        this.password=Password;
    }
    
   
   
    
}
