package com.nhnacademy.springmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentsRequest;
import com.nhnacademy.springmvc.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.repository.StudentRepository;
import com.nhnacademy.springmvc.repository.StudentRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.util.NestedServletException;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StudentRestControllerTest {

    MockMvc mockMvc;
    StudentRepository studentRepository;
    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        studentRepository = mock(StudentRepository.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new StudentRestController(studentRepository))
                        .addFilters(new CharacterEncodingFilter("UTF-8", true))
                        .build();
        mapper = new ObjectMapper();
    }

    @Test
    void getStudent_success() throws Exception {
        long id = 1L;
        Student student = new Student(id, "tmp", "tmp@email.com", 90, "Good!");

        when(studentRepository.exists(id)).thenReturn(true);
        when(studentRepository.getStudent(id)).thenReturn(student);

        mockMvc.perform(get("/students/{studentId}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getStudent_fail() {
        Throwable th = catchThrowable(() ->
                mockMvc.perform(get("/students/{studentId}", 2))
                        .andDo(print()));

        assertThat(th).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(StudentNotFoundException.class);
    }

    @Test
    void creatStudent() throws Exception {
        long id = 1L;
        StudentsRequest students = new StudentsRequest("테스트 추가 이름", "테스트 추가 이메일",
                90, "테스트 추가 코멘트");
        Student student = new Student(id, students.getName(), students.getEmail(), students.getScore(), students.getComment());

        when(studentRepository.exists(id)).thenReturn(true);
        when(studentRepository.getStudent(id)).thenReturn(student);

        mockMvc.perform(post("/students")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(students)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void putStudent_success() throws Exception {
        long id = 1L;
        StudentsRequest students = new StudentsRequest("이름 수정", "이메일 수정",
                20, "Bad!!");

        Student student = new Student(id, students.getName(), students.getEmail(), students.getScore(), students.getComment());

        when(studentRepository.exists(id)).thenReturn(true);
        when(studentRepository.getStudent(id)).thenReturn(student);

        mockMvc.perform(put("/students/{studentId}", 1)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(students)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void putStudent_fail() {
        long id = 1L;
        StudentsRequest students = new StudentsRequest("tmp", "tmp@email.com",
                10, "tmp");

        Student student = new Student(id, students.getName(), students.getEmail(), students.getScore(), students.getComment());

        when(studentRepository.exists(id)).thenReturn(true);
        when(studentRepository.getStudent(id)).thenReturn(student);

        Throwable th = catchThrowable(() ->
                mockMvc.perform(put("/students/{studentId}", 2)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(students)))
                        .andDo(print()));

        assertThat(th).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(StudentNotFoundException.class);
    }
}