// ProjectService.java
package com.HRM.HRmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HRM.HRmanager.dto.ProjectDto;

import com.HRM.HRmanager.info.ProjectInfo;

import com.HRM.HRmanager.model.Project;
import com.HRM.HRmanager.repository.EmployeeRepository;
import com.HRM.HRmanager.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeService employeeService;

    

    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectDto> projectDtos = new ArrayList<>();
        for (Project project : projects) {
            projectDtos.add(createDto(project));
        }
        return projectDtos;
    }

    @Transactional(readOnly = true)
    public ProjectDto getProjectDtoById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
        return createDto(project);
    }

    @Transactional
    public ProjectDto createProject(ProjectDto projectDto) {
        Project project = createEntity(projectDto);
        Project savedProject = projectRepository.save(project);
        return createDto(savedProject);
    }

    //doesn't change the employees
    @Transactional
    public ProjectInfo updateProject(Long id, ProjectInfo projectInfo) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
        project.setName(projectInfo.getName());
        project.setDescription(projectInfo.getDescription());
        Project updatedProject = projectRepository.save(project);
        return createInfo(updatedProject);
    }


    

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public Project createEntity(ProjectDto projectDto) {
        Project project = new Project();
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        if(projectDto.getHeadOfProject() == null){
            return project;
        }
        project.setHeadOfProject(employeeService.findOr404(projectDto.getHeadOfProject().getId()));
        return project;
    }

    public ProjectDto createDto(Project project) {
        System.err.println("AAAAAAAAAAAAAAAA");
        String ANSI_RED = "\u001B[31m";
        String ANSI_RESET = "\u001B[0m";
        
        System.err.println(ANSI_RED + "This is an error message." + ANSI_RESET);
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setDescription(project.getDescription());
        if (project.getHeadOfProject() == null){
            System.err.println("no head employee when creating the project");

        }
        else {
            System.err.println("error no" + project.getHeadOfProject());
            projectDto.setHeadOfProject(employeeService.createInfo(project.getHeadOfProject()));
        }
        return projectDto;
    }

    public ProjectInfo createInfo(Project project) {
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setId(project.getId());
        projectInfo.setName(project.getName());
        projectInfo.setDescription(project.getDescription());
        return projectInfo;
    }

    public Project findOr404(long Id) {
        return projectRepository.findById(Id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}
