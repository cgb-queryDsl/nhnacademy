package com.nhnacademy.edu.springframework.project.v2.repository;

import com.nhnacademy.edu.springframework.project.v2.service.Student;

import java.util.Collection;

public interface Students {
    void load();

    Collection<Student> findAll();

    void merge(Collection<Score> scores);
}
