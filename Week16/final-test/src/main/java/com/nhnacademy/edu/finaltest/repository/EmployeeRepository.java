package com.nhnacademy.edu.finaltest.repository;

import com.nhnacademy.edu.finaltest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    @Query(value = "select * from employees where department_code = :departmentId", nativeQuery = true)
    List<Employee> getEmployeesByDepartmentId(String departmentId);

    @Query("select e from Employee as e where e.department.code = :departmentId")
    List<Employee> getEmployeesByDepartmentIdV2(String departmentId);
}
