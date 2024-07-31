package com.HRM.HRmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.HRM.HRmanager.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
