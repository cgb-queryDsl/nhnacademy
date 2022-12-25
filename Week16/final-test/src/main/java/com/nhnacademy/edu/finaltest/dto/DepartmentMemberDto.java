package com.nhnacademy.edu.finaltest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepartmentMemberDto {
    private DepartmentDto department;
    private EmployeeDto employee;
}
