package com.HRM.HRmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.HRM.HRmanager.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
