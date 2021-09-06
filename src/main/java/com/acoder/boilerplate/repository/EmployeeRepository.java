package com.acoder.boilerplate.repository;

import com.acoder.boilerplate.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
