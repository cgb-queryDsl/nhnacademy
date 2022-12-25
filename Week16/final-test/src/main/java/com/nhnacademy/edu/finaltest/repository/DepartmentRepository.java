package com.nhnacademy.edu.finaltest.repository;

import com.nhnacademy.edu.finaltest.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {
}
