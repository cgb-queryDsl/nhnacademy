package com.nhnacademy.edu.finaltest.controller;

import com.nhnacademy.edu.finaltest.dto.DepartmentMemberDto;
import com.nhnacademy.edu.finaltest.service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class EmployeeRestController {

    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/department-members")
    public List<DepartmentMemberDto> getDepartmentMembers(@RequestHeader(value = "Content-Type") String contentType,
                                                          @RequestParam(value = "departmentIds") String departmentIds,
                                                          HttpServletResponse response) throws IOException {
        if (isJsonType(contentType)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return employeeService.getDepartmentMembers(departmentIds);
    }

    private boolean isJsonType(String contentType) {
        return !contentType.equals(MediaType.APPLICATION_JSON_VALUE);
    }
}
