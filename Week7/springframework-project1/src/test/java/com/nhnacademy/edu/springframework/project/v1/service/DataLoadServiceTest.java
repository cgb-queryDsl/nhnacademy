package com.nhnacademy.edu.springframework.project.v1.service;

import com.nhnacademy.edu.springframework.project.v1.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.v1.repository.Students;
import com.nhnacademy.edu.springframework.project.v1.service.CsvDataLoadService;
import com.nhnacademy.edu.springframework.project.v1.service.DataLoadService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class DataLoadServiceTest {

    private static final Students students = CsvStudents.getInstance();

    @Test
    void loadAndMerge() {
        // given
        DataLoadService service = new CsvDataLoadService();

        // when
        service.loadAndMerge();

        // then
        Assertions.assertThat(students.findAll()).hasSize(4);

        students.findAll().clear();
    }

    @AfterEach
    void clear() {
        students.findAll().clear();
    }
}