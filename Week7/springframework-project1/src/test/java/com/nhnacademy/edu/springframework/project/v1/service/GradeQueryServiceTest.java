package com.nhnacademy.edu.springframework.project.v1.service;

import com.nhnacademy.edu.springframework.project.v1.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.v1.repository.Score;
import com.nhnacademy.edu.springframework.project.v1.repository.Students;
import com.nhnacademy.edu.springframework.project.v1.service.CsvDataLoadService;
import com.nhnacademy.edu.springframework.project.v1.service.DataLoadService;
import com.nhnacademy.edu.springframework.project.v1.service.DefaultGradeQueryService;
import com.nhnacademy.edu.springframework.project.v1.service.GradeQueryService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class GradeQueryServiceTest {

    private static final DataLoadService dataLoadService = new CsvDataLoadService();
    private static final GradeQueryService gradeQueryService = new DefaultGradeQueryService();
    private static final Students students = CsvStudents.getInstance();

    @Test
    void getScoreByStudentName() {
        // given
        dataLoadService.loadAndMerge();

        // when
        List<Score> list = gradeQueryService.getScoreByStudentName("A");

        // then
        Assertions.assertThat(list.size()).isEqualTo(2);

        students.findAll().clear();
    }

    @Test
    void getScoreByStudentSeq() {
        // given
        dataLoadService.loadAndMerge();

        // when
        Score result = gradeQueryService.getScoreByStudentSeq(1);

        // then
        Assertions.assertThat(result).isInstanceOf(Score.class);

        students.findAll().clear();
    }

    @AfterEach
    void clear() {
        students.findAll().clear();
    }
}