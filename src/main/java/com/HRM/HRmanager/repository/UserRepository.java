package com.HRM.HRmanager.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.HRM.HRmanager.model.Employee;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Employee, Integer> {
    Optional<Employee> findByEmail(String email);
}