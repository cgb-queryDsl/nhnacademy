package com.nhnacademy.edu.springframework.project.v2.repository;

import com.nhnacademy.edu.springframework.project.v1.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.v1.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.v2.Main;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Main.class})
class StudentsTest {

    @Autowired
    private Students students;
    @Autowired
    private Scores scores;

    @Test
    void load() {
        students.load();

        Assertions.assertThat(students.findAll()).hasSize(4);
    }

    @Test
    void findAll() {
        students.load();

        Assertions.assertThat(students.findAll()).hasSize(4);
    }

    @Test
    void merge() {
        scores.load();
        students.load();

        // when
        students.merge(scores.findAll());

        // then
        Assertions.assertThat(students.findAll()).hasSize(4);
    }

}