package com.nhnacademy.edu.finaltest.service.impl;

import com.nhnacademy.edu.finaltest.entity.Department;
import com.nhnacademy.edu.finaltest.entity.Employee;
import com.nhnacademy.edu.finaltest.repository.DepartmentRepository;
import com.nhnacademy.edu.finaltest.repository.EmployeeRepository;
import com.nhnacademy.edu.finaltest.service.DataSaver;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataSaverImpl implements DataSaver {

    public static final Long DATA_MAXIMUM = 1000L;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public DataSaverImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void dataSave(List<String> data) {
        Long dataCount = 0L;
        for (String line : data) {
            line = line.replace("|", ",");
            String [] tmp = line.split(",");

            if (isDivideLine(tmp))
                continue;
            if (dataCount >= DATA_MAXIMUM)
                break;

            String employeeId = tmp[1];
            String employeeName = tmp[2];
            String departmentName = tmp[3];
            String departmentCode = tmp[4];

            Department department = insertDepartment(departmentName, departmentCode);
            insertEmployee(employeeId, employeeName, department);

            dataCount++;
        }
    }

    private void insertEmployee(String employeeId, String employeeName, Department department) {
        Employee employee = new Employee(employeeId, employeeName, department);
        employeeRepository.save(employee);
    }

    private Department insertDepartment(String departmentName, String departmentCode) {
        Department department = new Department(departmentCode, departmentName);
        departmentRepository.save(department);
        return department;
    }

    private boolean isDivideLine(String[] tmp) {
        return tmp[1].equals("사번") || tmp[1].equals("----------------");
    }
}

