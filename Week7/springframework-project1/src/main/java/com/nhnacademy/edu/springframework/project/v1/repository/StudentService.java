package com.nhnacademy.edu.springframework.project.v1.repository;

import com.nhnacademy.edu.springframework.project.v1.service.Student;

import java.util.Collection;

public interface StudentService {
    Collection<Student> getPassedStudents();

    Collection<Student> getStudentsOrderByScore();
}
