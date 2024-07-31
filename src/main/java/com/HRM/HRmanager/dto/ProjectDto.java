// ProjectDto.java
package com.HRM.HRmanager.dto;

import com.HRM.HRmanager.info.EmployeeInfo;

public class ProjectDto {
    private long id;
    private String name;
    private String description;
    private EmployeeInfo headOfProject;

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

    public EmployeeInfo getHeadOfProject() {
        return headOfProject;
    }

    public void setHeadOfProject(EmployeeInfo headOfProject) {
        this.headOfProject = headOfProject;
    }
}
