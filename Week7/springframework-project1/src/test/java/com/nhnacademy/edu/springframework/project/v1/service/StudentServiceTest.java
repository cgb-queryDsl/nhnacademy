package com.nhnacademy.edu.springframework.project.v1.service;

import com.nhnacademy.edu.springframework.project.v1.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.v1.repository.Score;
import com.nhnacademy.edu.springframework.project.v1.repository.Students;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;

class StudentServiceTest {

    private static final Students students = CsvStudents.getInstance();
    private static final DataLoadService dataLoadService = new CsvDataLoadService();
    private static final DefaultStudentService studentService = new DefaultStudentService();

    @Test
    @DisplayName("4번 seq : Score 객체를 추가시켜 성공하는 getPassedStudents Test")
    void getPassedStudents() {
        // given
        dataLoadService.loadAndMerge();
        Score newScore = new Score(4, 65);

        for(Student s : students.findAll()) {
            if (s.getScore() == null){
                s.setScore(newScore);
            }
        }

        // when
        Collection<Student> passedStudents = studentService.getPassedStudents();

        // then
        Assertions.assertThat(passedStudents.size()).isEqualTo(3);

        students.findAll().clear();
    }

    @Test
    @DisplayName("seq 개수가 달라 Null Exception 나오는 getPassedStudentsFail Test")
    void getPassedStudentsFail() {
        // given
        dataLoadService.loadAndMerge();

        // when

        // then
        Assertions.assertThatThrownBy(() -> studentService.getPassedStudents())
                .isInstanceOf(NullPointerException.class);

        students.findAll().clear();
    }

    @Test
    @DisplayName("4번 seq : Score 객체를 추가시켜 성공하는 getStudentsOrderByScore Test")
    void getStudentsOrderByScore() {
        // given
        dataLoadService.loadAndMerge();
        Score newScore = new Score(4, 99);
        boolean check = false;

        for(Student s : students.findAll()) {
            if (s.getScore() == null){
                s.setScore(newScore);
            }
        }

        // when
        Collection<Student> list = studentService.getStudentsOrderByScore();

        // then
        int tempNum = Integer.MIN_VALUE;
        for(Student s : list) {
            if(s.getScore().getScore() > tempNum) {
                check = false;
                tempNum = s.getScore().getScore();
            } else {
                check = true;
                tempNum = s.getScore().getScore();
            }
        }

        Assertions.assertThat(check).isEqualTo(true);

        students.findAll().clear();
    }

    @Test
    @DisplayName("seq 개수가 달라 Null Exception 나오는 getStudentsOrderByScore Test")
    void getStudentsOrderByScoreFail() {
        // given
        dataLoadService.loadAndMerge();

        // when

        // then
        Assertions.assertThatThrownBy(() -> studentService.getStudentsOrderByScore())
                .isInstanceOf(NullPointerException.class);

        students.findAll().clear();
    }

    @AfterEach
    void clear() {
        students.findAll().clear();
    }
}