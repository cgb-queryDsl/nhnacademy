package com.nhnacademy.edu.springframework.project.v1.repository;

import com.nhnacademy.edu.springframework.project.v1.service.Student;

import java.util.Collection;

public interface Students {
    void load();

    Collection<Student> findAll();

    void merge(Collection<Score> scores);
}
