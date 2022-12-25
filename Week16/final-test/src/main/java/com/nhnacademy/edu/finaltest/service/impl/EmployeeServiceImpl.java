package com.nhnacademy.edu.finaltest.service.impl;

import com.nhnacademy.edu.finaltest.dto.DepartmentDto;
import com.nhnacademy.edu.finaltest.dto.DepartmentMemberDto;
import com.nhnacademy.edu.finaltest.dto.EmployeeDto;
import com.nhnacademy.edu.finaltest.entity.Employee;
import com.nhnacademy.edu.finaltest.repository.EmployeeRepository;
import com.nhnacademy.edu.finaltest.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<DepartmentMemberDto> getDepartmentMembers(String departmentId) {
        List<Employee> employees = employeeRepository.getEmployeesByDepartmentId(departmentId);
        List<DepartmentMemberDto> result = new ArrayList<>();

        for (Employee employee : employees) {
            result.add(new DepartmentMemberDto(setDepartment(employee), setEmployee(employee)));
        }

        return result.stream()
                .sorted(Comparator.comparing((DepartmentMemberDto d) -> d.getDepartment().getId()))
                .sorted(Comparator.comparing((DepartmentMemberDto d) -> d.getEmployee().getId()))
                .collect(Collectors.toList());
    }

    private EmployeeDto setEmployee(Employee employee) {
        return new EmployeeDto(employee.getId(), employee.getName());
    }

    private DepartmentDto setDepartment(Employee employee) {
        return new DepartmentDto(employee.getDepartment().getCode(), employee.getDepartment().getCodeName());
    }
}
