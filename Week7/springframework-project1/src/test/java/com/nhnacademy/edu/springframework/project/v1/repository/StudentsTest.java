package com.nhnacademy.edu.springframework.project.v1.repository;

import com.nhnacademy.edu.springframework.project.v1.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.v1.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.v1.repository.Scores;
import com.nhnacademy.edu.springframework.project.v1.repository.Students;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentsTest {

    private static final Scores scores = CsvScores.getInstance();
    private static final Students students = CsvStudents.getInstance();

    @Test
    @DisplayName("student.csv load() 성공 Test")
    void load() {
        // given

        // when
        students.load();

        // then
        Assertions.assertThat(students.findAll()).hasSize(4);

        students.findAll().clear();
    }

    @Test
    void findAll() {
        // given

        // when
        students.load();

        // then
        Assertions.assertThat(students.findAll()).hasSize(4);

        students.findAll().clear();
    }

    @Test
    void merge() {
        // given
        scores.load();
        students.load();

        // when
        students.merge(scores.findAll());

        // then
        Assertions.assertThat(students.findAll()).hasSize(4);
        students.findAll().clear();
    }

    @AfterEach
    void clear() {
        scores.findAll().clear();
        students.findAll().clear();
    }
}