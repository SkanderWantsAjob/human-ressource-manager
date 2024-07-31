package com.HRM.HRmanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String description;

    
    @JoinColumn(name = "head_of_project_id" )
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee headOfProject;

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public Employee getHeadOfProject() {
        return headOfProject;
    }

    public void setHeadOfProject(Employee headOfProject) {
        this.headOfProject = headOfProject;
    }

}
