package com.nhnacademy.edu.finaltest.service;

import com.nhnacademy.edu.finaltest.dto.DepartmentMemberDto;

import java.util.List;

public interface EmployeeService {
    List<DepartmentMemberDto> getDepartmentMembers(String departmentId);
}
