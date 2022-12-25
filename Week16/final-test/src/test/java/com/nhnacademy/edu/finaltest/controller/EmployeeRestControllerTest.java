package com.nhnacademy.edu.finaltest.controller;

import com.nhnacademy.edu.finaltest.dto.DepartmentDto;
import com.nhnacademy.edu.finaltest.dto.DepartmentMemberDto;
import com.nhnacademy.edu.finaltest.dto.EmployeeDto;
import com.nhnacademy.edu.finaltest.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EmployeeRestControllerTest {

    MockMvc mockMvc;
    EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = mock(EmployeeService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new EmployeeRestController(employeeService)).build();
    }

    @Test
    @DisplayName("부서 직원 조회 성공 테스트")
    void getEmployee_success() throws Exception {
        // given
        String departmentIds = "L1001";

        // when
        List<DepartmentMemberDto> departmentMembers = List.of(new DepartmentMemberDto(
                new DepartmentDto("L1001", "백엔드1팀"),
                new EmployeeDto("20200001", "김영일")));
        when(employeeService.getDepartmentMembers(departmentIds)).thenReturn(departmentMembers);

        // then
        mockMvc.perform(get("/department-members")
                        .param("departmentIds", departmentIds)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].department.id", equalTo(departmentIds)))
                .andExpect(jsonPath("$[0].employee.name", equalTo("김영일")))
                .andDo(print());
    }

    @Test
    void noParam_fail_400() throws Exception {
        mockMvc.perform(get("/department-members")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void contentType_is_text_fail_400() throws Exception {
        // given
        String departmentIds = "L1001";

        // when
        List<DepartmentMemberDto> departmentMembers = List.of(new DepartmentMemberDto(
                new DepartmentDto("L1001", "백엔드1팀"),
                new EmployeeDto("20200001", "김영일")));
        when(employeeService.getDepartmentMembers(departmentIds)).thenReturn(departmentMembers);

        // then
        mockMvc.perform(get("/department-members")
                        .param("departmentIds", departmentIds)
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void no_contentType_fail_400() throws Exception {
        // given
        String departmentIds = "L1001";

        // when
        List<DepartmentMemberDto> departmentMembers = List.of(new DepartmentMemberDto(
                new DepartmentDto("L1001", "백엔드1팀"),
                new EmployeeDto("20200001", "김영일")));
        when(employeeService.getDepartmentMembers(departmentIds)).thenReturn(departmentMembers);

        // then
        mockMvc.perform(get("/department-members")
                        .param("departmentIds", departmentIds))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

}