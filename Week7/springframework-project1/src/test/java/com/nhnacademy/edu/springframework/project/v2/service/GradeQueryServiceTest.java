package com.nhnacademy.edu.springframework.project.v2.service;

import com.nhnacademy.edu.springframework.project.v2.Main;
import com.nhnacademy.edu.springframework.project.v2.repository.Score;
import com.nhnacademy.edu.springframework.project.v2.repository.Students;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Main.class})
class GradeQueryServiceTest {

    @Autowired
    private DataLoadService dataLoadService;
    @Autowired
    private GradeQueryService gradeQueryService;
    @Autowired
    private Students students;

    @Test
    void getScoreByStudentName() {
        dataLoadService.loadAndMerge();

        List<Score> list = gradeQueryService.getScoreByStudentName("A");

        Assertions.assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void getScoreByStudentSeq() {
        dataLoadService.loadAndMerge();

        Score result = gradeQueryService.getScoreByStudentSeq(1);

        Assertions.assertThat(result).isInstanceOf(Score.class);
    }

}