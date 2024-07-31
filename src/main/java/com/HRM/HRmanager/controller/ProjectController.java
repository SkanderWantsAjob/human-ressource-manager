package com.HRM.HRmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.HRM.HRmanager.dto.ProjectDto;
import com.HRM.HRmanager.info.ProjectInfo;
import com.HRM.HRmanager.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        ProjectDto projectDto = projectService.getProjectDtoById(id);
        return ResponseEntity.ok(projectDto);
    }

    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        ProjectDto createdProject = projectService.createProject(projectDto);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectInfo> updateProject(@PathVariable Long id, @RequestBody ProjectInfo projectInfo) {
        ProjectInfo updatedProject = projectService.updateProject(id, projectInfo);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
