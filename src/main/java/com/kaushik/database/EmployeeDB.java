package com.kaushik.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaushik.models.Employee;

public interface EmployeeDB extends JpaRepository<Employee, String> {
    Employee findByEmail(String email);
}
